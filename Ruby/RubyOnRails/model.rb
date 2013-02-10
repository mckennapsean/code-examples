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

# Ruby on Rails, model component of MVC framework
# not intended to be used alone

class Video < ActiveRecord::Base
  attr_accessible :embed_code, :title
  validates :title, :embed_code, :presence => true
  validate :must_have_valid_embed_code

  has_many :comments

  def must_have_valid_embed_code
  	unless embed_code.include?("<iframe")
  		errors.add(:embed_code, "Must include an iframe tag")
  	end
  end
end
