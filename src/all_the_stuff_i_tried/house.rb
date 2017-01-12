class House
  attr_accessor :house_number
  attr_accessor :colours
  attr_accessor :nationalities
  attr_accessor :pets
  attr_accessor :beverages
  attr_accessor :cigarettes

  def initialize(house_number)
    # Instance variables
    @house_number = house_number
    @colours = [:red, :green, :white, :yellow, :blue]
    @nationalities = [:briton, :swede, :dane, :german, :norwegian]
    @pets = [:dog, :bird, :cat, :horse, :fish]
    @beverages = [:tea, :coffee, :milk, :beer, :water]
    @cigarettes = [:pall_mall, :dunhill, :malboro, :winfield, :rothmanns]
  end

  def ==(other_house)
    self.class == other_house.class && state == other_house.state
  end

  protected

  def state
    instance_variables.map { |variable| instance_variable_get(variable) }
  end
end
