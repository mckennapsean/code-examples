# by Sean McKenna
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
