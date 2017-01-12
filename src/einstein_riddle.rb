require './tuple_list'
require 'pry'

# sagen wir mal ich hätte einen algo mit
# Variablen: colours, nationalities, pets, beverages, cigarettes
# Domain der jeweilige Wertebereich
# Value = einzelner Wert der Domain, zB red
#
# Tuple in colours dann zB (1,red), (2,red), (3,red) usw usf für alle Hausnummern und alle Farben jeweils
# Q = alle Variablentuple, für die es Constraints gibt, also (colour, nation) usw.
#
# revise aufrufen mit variablen farbe und nation, in Q werden alle Tuple gepackt,
# die aktuell noch in farbe und nation sind
# => über alle Tuple von Var1, also Farbe, iterieren und checken, ob constraint damit erfüllt, sonst löschen

# in ac3 packe ich alle constraint tuple rein (= FullList), für die es halt constraint gibt, also (farbe, nation), (farbe, drink) usw
# kopie von dieser liste machen, das dann Q
# aus Q erstes Elem rauslöschen und damit revise aufrufen (hier die constraints geprüft)
# wenn tuple gelöscht wurde aus Vk (zB Farbe), wird über FullList iteriert, geprüft,
# ob Farbe noch in anderen Tuples enthalten ist zb (Zigarette, Farbe),
# wenn ja und diese Tuples nicht in aktueller Q sind, werden sie wieder hinzugefügt
# so lange bis Q leer oder nicht konsistent. wenn Q leer und consistent, true zurück, Algo durch

# colours = TupleList.new([:red, :green, :white, :yellow, :blue])
# nationalities = TupleList.new([:briton, :swede, :dane, :german, :norwegian])
#
# puts colours.inspect
# puts nationalities.inspect

class EinsteinRiddle
  # list of value tuples for each domain
  attr_accessor :colours
  attr_accessor :nationalities
  attr_accessor :pets
  attr_accessor :beverages
  attr_accessor :cigarettes

  def initialize
    @colours =  TupleList.new([:red, :green, :white, :yellow, :blue])
    @nationalities = TupleList.new([:briton, :swede, :dane, :german, :norwegian])
    @pets = TupleList.new([:dog, :bird, :cat, :horse, :fish])
    @beverages = TupleList.new([:tea, :coffee, :milk, :beer, :water])
    @cigarettes = TupleList.new([:pall_mall, :dunhill, :malboro, :winfield, :rothmanns])
  end

  def solve
    # advanced optimization
    # 7. Der Mann im mittleren Haus trinkt Milch.
    @beverages.set_value([3, :milk])

    # 9. Der Norweger lebt im ersten Haus.
    @nationalities.set_value([1, :norwegian])

    # 13. Der Norweger wohnt neben dem blauen Haus.
    @colours.set_value([2, :blue])

    # list of variable tuples for which constraints exist
    constraint_tuples = generate_constraint_tuples
    ac_3(constraint_tuples)
    # nun ac_3_look_ahead ?

    # constraint_tuples.each { |constraint_tuple| ac_3(constraint_tuple) }
  end


  private

  def ac_3(constraint_tuples)
    queue_constraint_tuples = constraint_tuples.clone

    while !queue_constraint_tuples.empty? do
      current_constraint = queue_constraint_tuples.delete_at(0)

      if revise(current_constraint)
        return false if send(current_constraint.first).tuples.empty?
        queue_constraint_tuples |= affected_by_revise(current_constraint, constraint_tuples)
      end
    end

    return true
  end

  # checks if there are consistent values for given constraint tuple
  # deletes inconsistent values from domain of first variable in constraint tuple
  # returns if a value has been deleted or not
  def revise(constraint_tuple)
    # e.g. vi = :colours, vj = :nationalities
    vi, vj = constraint_tuple
    delete_flag = false
    values_vi = send(vi).tuples

    values_vi.each do |value_tuple|
      domain_vj = send(vj).tuples
      constraint_function = "constraints_#{vi.to_s}_#{vj.to_s}"
      # call constraints according to given constraint_tuple
      consistent = send(constraint_function, value_tuple, domain_vj)

      if !consistent
        values_vi.delete(value_tuple)
        delete_flag = true
      end
    end

    delete_flag
  end

  def generate_constraint_tuples
    tuples = []

    tuples.push([:colours, :colours])
    tuples.push([:colours, :cigarettes])
    tuples.push([:colours, :beverages])
    tuples.push([:colours, :nationalities])
    tuples.push([:nationalities, :colours])
    tuples.push([:nationalities, :pets])
    tuples.push([:nationalities, :cigarettes])
    tuples.push([:nationalities, :beverages])
    tuples.push([:beverages, :nationalities])
    tuples.push([:beverages, :colours])
    tuples.push([:beverages, :cigarettes])
    tuples.push([:cigarettes, :beverages])
    tuples.push([:cigarettes, :nationalities])
    tuples.push([:cigarettes, :colours])
    tuples.push([:cigarettes, :pets])
    tuples.push([:pets, :cigarettes])
    tuples.push([:pets, :nationalities])

    tuples
  end

  def affected_by_revise(constraint_tuple, queue_constraint_tuples)
    vk, vm = constraint_tuple

    queue_constraint_tuples.select do |tuple|
      vi, vkk = tuple

      vk == vkk && vi != vm
    end
  end

  # def neighbors?(tuple_1, tuple_2)
  #   left_of?(tuple_1, tuple_2) || left_of?(tuple_2, tuple_1)
  # end
  #
  # # checks if house in tuple_1 is left of house in tuple_2
  # def left_of?(tuple_1, tuple_2)
  #   house_1 = tuple_1.first
  #   house_2 = tuple_2.first
  #
  #   house_2 - house_1 == 1
  # end

  # e.g.
  # value_tuple_vi = [3, :green]
  # domain_vj = [[4, :white], [5, :white], [2, :blue], ...]
  # checks if in domain of :colours exists a tuple, where [4, :white]
  def constraints_colours_colours(value_tuple_vi, domain_vj)
    # 4. Das gruene Haus steht links neben dem weissen Haus. noch einbringen
    house_i, colour_i = value_tuple_vi

    return domain_vj.include?([house_i + 1, :white]) if colour_i == :green
    return domain_vj.include?([house_i - 1, :green]) if colour_i == :white
  end

  def constraints_colours_cigarettes(value_tuple_vi, domain_vj)
    # 8. Der Bewohner des gelben Hauses raucht Dunhill.
    house_vi, value_vi = value_tuple_vi

    return domain_vj.include?([house_vi, :dunhill]) if value_vi == :yellow
    return domain_vj.include?([house_vi, :yellow]) if value_vi == :dunhill
  end

  def constraints_colours_beverages(value_tuple_vi, domain_vj)
    # 5. Der Besitzer des gruenen Hauses trinkt Kaffee.
    house_vi, value_vi = value_tuple_vi

    return domain_vj.include?([house_vi, :coffee]) if value_vi == :green
    return domain_vj.include?([house_vi, :green]) if value_vi == :coffee
  end

  def constraints_colours_nationalities(value_tuple_vi, domain_vj)
    # 1. Der Brite lebt im roten Haus.
    house_vi, value_vi = value_tuple_vi

    return domain_vj.include?([house_vi, :briton]) if value_vi == :red
    return domain_vj.include?([house_vi, :red]) if value_vi == :briton
  end

  def constraints_nationalities_pets(value_tuple_vi, domain_vj)
    # 2. Der Schwede hält sich einen Hund.
    house_vi, value_vi = value_tuple_vi

    return domain_vj.include?([house_vi, :dog]) if value_vi == :swede
    return domain_vj.include?([house_vi, :swede]) if value_vi == :dog
  end

  def constraints_nationalities_cigarettes(value_tuple_vi, domain_vj)
    # 14. Der Deutsche raucht Rothmanns.
    house_vi, value_vi = value_tuple_vi

    return domain_vj.include?([house_vi, :rothmanns]) if value_vi == :german
    return domain_vj.include?([house_vi, :german]) if value_vi == :rothmanns
  end

  def constraints_nationalities_beverages(value_tuple_vi, domain_vj)
    # 3. Der Daene trinkt gern Tee.
    house_vi, value_vi = value_tuple_vi

    return domain_vj.include?([house_vi, :tea]) if value_vi == :dane
    return domain_vj.include?([house_vi, :dane]) if value_vi == :tea
  end

  def constraints_beverages_cigarettes(value_tuple_vi, domain_vj)
    house_vi, value_vi = value_tuple_vi
    found = false

    # 12. Der Winfield-Raucher trinkt gern Bier.
    found = domain_vj.include?([house_vi, :winfield]) if value_vi == :beer
    found = domain_vj.include?([house_vi, :beer]) if value_vi == :winfield

    # 15. Der Malboro-Raucher hat einen Nachbarn, der Wasser trinkt.
    if value_vi == :water
      found = domain_vj.include?([house_vi + 1, :malboro]) || domain_vj.include?([house_vi - 1, :malboro])
    elsif value_vi == :malboro
      found = domain_vj.include?([house_vi + 1, :water]) || domain_vj.include?([house_vi - 1, :water])
    end

    found
  end

  def constraints_cigarettes_pets(value_tuple_vi, domain_vj)
    house_vi, value_vi = value_tuple_vi
    found = false

    # 6. Die Person, die Pall Mall raucht, hat einen Vogel.
    found = domain_vj.include?([house_vi, :bird]) if value_vi == :pall_mall
    found = domain_vj.include?([house_vi, :pall_mall]) if value_vi == :bird

    # 10. Der Malboro-Raucher wohnt neben der Person mit der Katze.
    if value_vi == :cat
      found = domain_vj.include?([house_vi + 1, :malboro]) || domain_vj.include?([house_vi - 1, :malboro])
    elsif value_vi == :malboro
      found = domain_vj.include?([house_vi + 1, :cat]) || domain_vj.include?([house_vi - 1, :cat])
    end

    # 11. Der Mann mit dem Pferd lebt neben der Person, die Dunhill raucht.
    if value_vi == :horse
      found = domain_vj.include?([house_vi + 1, :dunhill]) || domain_vj.include?([house_vi - 1, :dunhill])
    elsif value_vi == :dunhill
      found = domain_vj.include?([house_vi + 1, :horse]) || domain_vj.include?([house_vi - 1, :horse])
    end

    found
  end
end
