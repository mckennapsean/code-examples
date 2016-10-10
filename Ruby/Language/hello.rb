# classic Hello, World program

# greeter class, say hi to someone!
class Greeter
	def initialize(name)
		@name = name.capitalize
	end
  
	def salute
		puts "Hello #{@name}!"
	end
end

# create new greeter
g = Greeter.new("world")

# ouput "Hello World"
g.salute
