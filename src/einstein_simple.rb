#!/usr/bin/env ruby

@colours        = [:white, :yellow, :blue, :red, :green].shuffle.permutation
@cigarettes     = [:malboro, :pall_mall, :rothmanns, :winfield, :dunhill].shuffle.permutation
@nationalities  = [:german, :swede, :briton, :norwegian, :dane].shuffle.permutation
@beverages      = [:beer, :water, :tea, :milk, :coffee].shuffle.permutation
@pets           = [:bird, :cat, :horse, :fish, :dog].shuffle.permutation

def next_to?(set_a, val_a, set_b, val_b)
  left_of?(set_a, val_a, set_b, val_b) ||
  left_of?(set_b, val_b, set_a, val_a)
end

def left_of?(set_a, val_a, set_b, val_b)
  (0..4).any? do |i|
    set_a[i]   == val_a &&
    set_b[i+1] == val_b
  end
end

def implies?(set_a, val_a, set_b, val_b)
  (0..4).any? do |i|
    set_a[i] == val_a &&
    set_b[i] == val_b
  end
end

def solve
  @colours.each do |colours|
    next unless left_of?(colours, :green, colours, :white)
    next unless colours[1] == :blue
    @nationalities.each do |nationalities|
      next unless implies?(nationalities, :briton, colours, :red)
      next unless nationalities[0] == :norwegian
      # next unless next_to?(nationalities, :norwegian, colours, :blue)
      @pets.each do |pets|
        next unless implies?(nationalities, :swede, pets, :dog)
        @beverages.each do |beverages|
          next unless beverages[2] == :milk
          next unless implies?(colours, :green, beverages, :coffee)
          next unless implies?(nationalities, :dane, beverages, :tea)
          @cigarettes.each do |cigarettes|
            next unless next_to?(pets, :horse, cigarettes, :dunhill)
            next unless implies?(cigarettes, :pall_mall, pets, :bird)
            next unless next_to?(cigarettes, :malboro, beverages, :water)
            next unless next_to?(cigarettes, :malboro, pets, :cat)
            next unless implies?(nationalities , :german, cigarettes, :rothmanns)
            next unless implies?(colours, :yellow, cigarettes, :dunhill)
            next unless implies?(cigarettes, :winfield,  beverages, :beer)
            return [colours, nationalities, pets, beverages, cigarettes]
          end
        end
      end
    end
  end
end

class Symbol
  def humanize
    result = self.to_s
    result.gsub!('_', ' ')
    result.split(' ').collect{|part| part.capitalize }.join(' ')
  end
end

solution = solve

for i in (0..4)
  number, color, nationality, cigar, drink, pet = i+1, solution[0][i], solution[1][i].humanize, solution[4][i].humanize, solution[3][i], solution[2][i]
  puts "House #{number} is #{color}. The owner is a #{nationality}, smokes #{cigar}, drinks #{drink} and keeps a #{pet}."
end

puts "\n"
