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
