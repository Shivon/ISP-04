require 'pry'

# One tupel looks like [1, :red] with 1 being the house number and :red being the value for the domain
class TupelList
  attr_accessor :tupels

  def initialize(domains)
    @tupels = domains.collect_concat do |domain|
      5.times.map { |i| [i+1, domain] }
    end
  end

  # returns each tupel which contains given value
  def with(value)
    @tupels.select do |tupel|
      tupel.include?(value)
    end
  end

  def delete_except(value, exception)
    delete_tupels = with(value)

    @tupels.delete_if do |tupel|
      delete_tupels.include?(tupel) && tupel != exception
    end
  end

  # sets value for domain by given tupel
  def set_value(tupel)
    delete_except(tupel.first, tupel)
    delete_except(tupel.last, tupel)
  end
end
