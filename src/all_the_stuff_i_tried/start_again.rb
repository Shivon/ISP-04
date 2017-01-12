#!/usr/bin/env ruby
require 'pry'

@colours        = [:red, :green, :white, :yellow, :blue].shuffle.permutation
@nationalities  = [:briton, :swede, :dane, :german, :norwegian].shuffle.permutation
@pets           = [:dog, :bird, :cat, :horse, :fish].shuffle.permutation
@beverages      = [:tea, :coffee, :milk, :beer, :water].shuffle.permutation
@cigarettes     = [:pall_mall, :dunhill, :malboro, :winfield, :rothmanns].shuffle.permutation


def generate_tuples
  tuples = []

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

# AC3 LOOKAHEAD => TODO!
# Vcv = next variable, procedure returns true when there are values for this variable which are consistent
# (and only those values are afterwards still existent within the variable's domain)
#
# procedure AC3-LA(cv)
#   Q <- {(Vi,Vcv) in arcs(G),i>cv}; # !!!!! i>cv !!!!! nur für folgende Variablen testen
#   consistent <- true;
#
#   while not Q empty & consistent
#     select and delete any arc (Vk,Vm) from Q;
#     if REVISE(Vk,Vm) then
#       Q <- Q union {(Vi,Vk) such that (Vi,Vk) in arcs(G),i#k,i#m,i>cv}
#       consistent <- not Dk empty
#     endif
#   endwhile
#
#   return consistent
# end AC3-LA

def constraints
  # 4. Das gruene Haus steht links neben dem weissen Haus.


  # 1. Der Brite lebt im roten Haus.
  # 2. Der Schwede hält sich einen Hund.
  # 3. Der Daene trinkt gern Tee.
  # 5. Der Besitzer des gruenen Hauses trinkt Kaffee.
  # 6. Die Person, die Pall Mall raucht, hat einen Vogel.
  # 7. Der Mann im mittleren Haus trinkt Milch.
  #
  # 8. Der Bewohner des gelben Hauses raucht Dunhill.
  #
  # 9. Der Norweger lebt im ersten Haus.
  #
  # 10. Der Malboro-Raucher wohnt neben der Person mit der Katze.
  # 11. Der Mann mit dem Pferd lebt neben der Person, die Dunhill raucht.
  # 12. Der Winfield-Raucher trinkt gern Bier.
  #
  # 13. Der Norweger wohnt neben dem blauen Haus.
  #
  # 14. Der Deutsche raucht Rothmanns.
  # 15. Der Malboro-Raucher hat einen Nachbarn, der Wasser trinkt.
  [
    [nationalities: :briton, colours: :red],
    [nationalities: :swede, pets: :dog],
    [nationalities: :dane, beverages: :tea],
    [colours: :green, beverages: :coffee],
    [cigarettes: :pall_mall, pets: :bird],
  ]

end
