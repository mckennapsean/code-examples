# by Sean McKenna

# Greeter class
class Greeter
	def initialize(name)
		@name=name.capitalize
	end

	def salute
		puts "Hello #{@name}!"
	end
end

#Create new object
g=Greeter.new("world")

#Ouput "Hello World"
g.salute
