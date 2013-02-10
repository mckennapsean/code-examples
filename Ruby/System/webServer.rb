## Copyright 2013 Sean McKenna
## 
##    Licensed under the Apache License, Version 2.0 (the "License");
##    you may not use this file except in compliance with the License.
##    You may obtain a copy of the License at
## 
##        http://www.apache.org/licenses/LICENSE-2.0
## 
##    Unless required by applicable law or agreed to in writing, software
##    distributed under the License is distributed on an "AS IS" BASIS,
##    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
##    See the License for the specific language governing permissions and
##    limitations under the License.
##

# a running, multi-threaded web server
# needs HTML files to serve, as well

require 'socket'

# server settings
port = 3389
server = TCPServer.open('localhost',port)

# HTTP server object
class HTTPServer
  def initialize(session,request)
    @session = session
    @request = request
  end
  
  # load the entire filename of the input file, or sample.html if not specified
  def get_full_path
    filename = @request =~ /^GET\s\/+(\S+)/i ? $1 : ""
    if filename == ""
      filename = "sample.html"
    end
    filename = File.expand_path(filename,@defaultPath)
    return filename
  end
  
  # send the HTTP response and, if necessary, the page information
  def serve
    @fullPath = get_full_path
    src = nil
    begin
      if File.exists?(@fullPath) and File.file?(@fullPath)
        
        # ensure path starts with base path
        if @fullPath.index('') == 0
          content_type = get_content_type(@fullPath)
          @session.print "HTTP/1.1 200/OK\r\nContent-type: #{content_type}\r\n\r\n"
          src = File.open(@fullPath,"rb")
          while(not src.eof?)
            buffer = src.read(256)
            @session.write(buffer)
          end
        src.close
        src = nil
        else
          # should send 403 Forbidden Access
          @session.print "HTTP/1.1 404/Object Not Found\r\nContent-type: text/html\r\n\r\n"
          @session.print "<html> <head> <title> Not Found </title> </head> <body> Not Found </body> </html>"
        end
      else
          @session.print "HTTP/1.1 404/Object Not Found\r\nContent-type: text/html\r\n\r\n"
          @session.print "<html> <head> <title> Not Found </title> </head> <body> Not Found </body> </html>"
      end
    ensure
      src.close unless src == nil
      @session.close
    end
  end
  
  # specifies the kind of content dependent on the filename extension
  def get_content_type(path)
    ext = File.extname(path)
    return "text/html"  if ext == ".html"  or ext == ".htm"
    return "text/plain" if ext == ".txt"
    return "text/css"    if ext == ".css"
    return "image/jpeg" if ext == ".jpg"  or ext == ".jpeg"
    return "image/gif"  if ext == ".gif"
    return "image/bmp"  if ext == ".bmp"
    return "text/plain" if ext == ".rb"
    return "text/xml"    if ext == ".xml"
    return "text/xml"    if ext == ".xsl"
    return "text/html"
  end
end

# start server, continually runs
loop do
  session = server.accept
  request = session.gets
  # multi-thread/client support
  Thread.start(session,request) do |session,request|
    HTTPServer.new(session,request).serve
  end
end
