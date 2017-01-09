require './house'

class EinsteinRiddle
  attr_accessor :house_assignment_1
  attr_accessor :house_assignment_2
  attr_accessor :house_assignment_3
  attr_accessor :house_assignment_4
  attr_accessor :house_assignment_5

  def initialize
    @house_assignment_1 = House.new
    @house_assignment_2 = House.new
    @house_assignment_3 = House.new
    @house_assignment_4 = House.new
    @house_assignment_5 = House.new
  end
end
