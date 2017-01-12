require 'pry'

# One tuple looks like [1, :red] with 1 being the house number and :red being the value for the domain
class TupleList
  attr_accessor :tuples

  def initialize(domains)
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
