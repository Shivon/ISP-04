require 'pry'

# One tuple looks like [1, :red] with 1 being the house number and :red being the value for the domain
class TupleList
  attr_accessor :tuples

  def initialize(domains)
    @domains = domains
    @tuples = domains.collect_concat do |domain|
      5.times.map { |i| [i+1, domain] }
    end
  end

  # returns each tuple which contains given value
  def with(value)
    @tuples.select do |tuple|
      tuple.include?(value)
    end
  end

  def definite_tuples
    tuples = []

    @domains.each do |domain|
      tuples_with_domain = with(domain)
      tuples += tuples_with_domain if tuples_with_domain.length == 1
    end

    (1..5).each do |house|
      tuples_with_house = with(house)
      tuples |= tuples_with_house if tuples_with_house.length == 1
    end

    tuples
  end

  def delete_except(value, exception)
    delete_tuples = with(value)

    @tuples.delete_if do |tuple|
      delete_tuples.include?(tuple) && tuple != exception
    end
  end

  # sets value for domain by given tuple
  def set_value(tuple)
    delete_except(tuple.first, tuple)
    delete_except(tuple.last, tuple)
  end
end
