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

# creates a UDP ping client
# can be paired with Java/System/UDPPingServer.java for testing

require 'socket'

# server information: IP address, port number, & timeout
SERVER = "127.0.0.1"
PORT = 1234
TIMEOUT = 1

class UDPPingClient
	def initialize(seq)
		@seq = seq
		@time_start = Time.now
	end
	
	# send out and process all the pings
	def request
		
		# send initial UDP ping packet
		socket = nil
		str = "PING "+@seq+" "+@time_start.to_f.to_s+"\r\n"
		begin
			socket = UDPSocket.open
			socket.send(str,0,SERVER,PORT)
			
			# receive packet (if not dropped) and calculate the RTT
			@test = 0
			while (Time.now.to_f - @time_start.to_f) < 1
				if select([socket],nil,nil,TIMEOUT)
					socket.recv(4096)
					@time_elapsed = (Time.now.to_f - @time_start.to_f)*1000
					@rtt = @time_elapsed.to_s
					@test = 1
				end
			end
			
			# output to client with RTT in ms
			puts "PING "+@seq+" Timeout - Network Error\r\n\r\n" if @test == 0
			puts "PING "+@seq+" "+@rtt+" ms\r\n\r\n" if @test == 1
			
		# cleanup
		rescue IOError, SystemCallError
		ensure
			socket.close if socket
		end
	end
end

# execution of program, ping a total of 10 times
for ct in 0..9 do
	count = ct.to_s
	UDPPingClient.new(count).request
end
puts "PING process complete"
