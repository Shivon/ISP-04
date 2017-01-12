require './house'
require 'pry'

class EinsteinRiddle
  attr_accessor :house_assignment_1
  attr_accessor :house_assignment_2
  attr_accessor :house_assignment_3
  attr_accessor :house_assignment_4
  attr_accessor :house_assignment_5

  def initialize
    @house_assignment_1 = House.new(1)
    @house_assignment_2 = House.new(2)
    @house_assignment_3 = House.new(3)
    @house_assignment_4 = House.new(4)
    @house_assignment_5 = House.new(5)
  end

  def solve
    # advanced optimization
    # 7. Der Mann im mittleren Haus trinkt Milch.
    @house_assignment_3.beverages = [:milk]
    remove_found_value(:milk, 'beverages', :@house_assignment_3)

    # 9. Der Norweger lebt im ersten Haus.
    @house_assignment_1.nationalities = [:norwegian]
    remove_found_value(:norwegian, 'nationalities', :@house_assignment_1)

    # 13. Der Norweger wohnt neben dem blauen Haus.
    @house_assignment_2.colours = [:blue]
    remove_found_value(:blue, 'colours', :@house_assignment_2)

    # 4. Das gruene Haus steht links neben dem weissen Haus.
    # TEIL: grün und weiß können dadurch nicht mehr Haus 1 sein
    # solution[:colours][:green] = [3, 4, 5]
    # solution[:colours][:white] = [3, 4, 5]
  end

  private

  # OLD !!!! TODO: neu implementieren, wenn nötig
  # checks for given attribute, e.g. :colours, if every value still has domains
  # {colours: {red: [5], blue: ...}} => value of each value must have at least an integer
  # def domainsExistent?(attribute)
  #   existent = true
  #   solution[attribute].each { |value, domain| existent = !domain.empty? }
  #
  #   existent
  # end

  # removes found value from the other houses domains (except from found_assignment)
  # remove_found_value(:blue, 'colours', :@house_assignment_2)
  def remove_found_value(value, variable, found_assignment)
    instance_variables.each do |house_assignment|
      instance_variable_get(house_assignment).send(variable).delete(value) unless house_assignment == found_assignment
    end
  end
end
