# by Sean McKenna on May 16th, 2010
# pingclient.rb aims to create a UDP pinging client in Ruby.
# It is to be used with UDPPingServer.java, for  CSC-317, a Java UDP server for pinging.

require 'socket'

#server information: IP address, port number, and timeout
SERVER = "127.0.0.1"
PORT = 1234
TIMEOUT = 1

class UDPPingClient
	def initialize(seq)
		@seq = seq
		@time_start = Time.now
	end

	#send out and process all the pings
	def request

		#send initial UDP ping packet
		socket = nil
		str = "PING "+@seq+" "+@time_start.to_f.to_s+"\r\n"
		begin
			socket = UDPSocket.open
			socket.send(str,0,SERVER,PORT)

			#receive packet (if not dropped) and calculate the RTT
			@test = 0
			while (Time.now.to_f - @time_start.to_f) < 1
				if select([socket],nil,nil,TIMEOUT)
					socket.recv(4096)
					@time_elapsed = (Time.now.to_f - @time_start.to_f)*1000
					@rtt = @time_elapsed.to_s
					@test = 1
				end
			end

			#output to client with RTT in ms
			puts "PING "+@seq+" Timeout - Network Error\r\n\r\n" if @test == 0
			puts "PING "+@seq+" "+@rtt+" ms\r\n\r\n" if @test == 1

		#cleanup
		rescue IOError, SystemCallError
		ensure
			socket.close if socket
		end
	end
end

#execution of program, ping a total of 10 times
for ct in 0..9 do
	count = ct.to_s
	UDPPingClient.new(count).request
end
puts "PING process complete"
