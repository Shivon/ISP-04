puts "Wow, I am an awesome script!"

# domains
HOUSES = [1, 2, 3, 4, 5]

COLOURS = [:red, :green, :white, :yellow, :blue]
NATIONALITIES = [:briton, :swede, :dane, :german, :norwegian]
PETS = [:dog, :bird, :cat, :horse, :fish]
BEVERAGES = [:tea, :coffee, :milk, :beer, :water]
CIGARETTES = [:pall_mall, :dunhill, :malboro, :winfield, :rothmanns]
HOUSE_ATTRIBUTES = [:colours, :nationalities, :pets, :beverages, :cigarettes]

def ac_3_look_ahead
  solution = init_attributes

  # advanced optimization
  # 7. Der Mann im mittleren Haus trinkt Milch.
  solution[:beverages][:milk] = [3]
  # 9. Der Norweger lebt im ersten Haus.
  solution[:nationalities][:norwegian] = [1]
  # 13. Der Norweger wohnt neben dem blauen Haus.
  solution[:colours][:blue] = [2]

  # puts generate_tuples
  # temp_tuples = Array.new(generate_tuples)

  tuples = generate_tuples
  temp_tuples = Array.new(tuples)

  consistent = true

  # tuples = [
  #   [:colours, :cigarettes], [:colours, :beverages], [:colours, :nationalities],
  #   [:nationalities, :colours], [:nationalities, :pets], [:nationalities, :cigarettes], [:nationalities, :beverages],
  #   [:beverages, :nationalities], [:beverages, :colours], [:beverages, :cigarettes],
  #   [:cigarettes, :beverages], [:cigarettes, :nationalities], [:cigarettes, :colours], [:cigarettes, :pets],
  #   [:pets, :cigarettes], [:pets, :nationalities]]

  while consistent && !temp_tuples.empty?
    current_tuple = temp_tuples.delete_at(0)

    variableK = current_tuple.first
    variableM = current_tuple.last

    if revise(variableK, variableM)
      # if REVISE(Vk,Vm) then Q <- Q union {(Vi,Vk) such that (Vi,Vk) in arcs(G),i#k,i#m,i>cv}
      temp_tuples |= tuples.select { |tuple| tuple.last == variableK && tuple.first != variableM }
      consistent = domainsExistent?(variableK, solution)
    end
  end

  # puts solution
  # puts "this is not the func you're looking for!"

  consistent
end


# TODO: impl
def revise

end

def init_attributes
  house_attributes = {}

  HOUSE_ATTRIBUTES.each do |attribute|
    house_attributes[attribute] = init_attribute(Object.const_get(attribute.to_s.upcase))
  end

  house_attributes
end

def init_attribute(domains)
  attribute = {}

  domains.each do |domain|
    attribute[domain] = HOUSES
  end

  attribute
end

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

# checks for given attribute, e.g. :colours, if every value still has domains
# {colours: {red: [5], blue: ...}} => value of each value must have at least an integer
def domainsExistent?(attribute, solution)
  existent = true
  solution[attribute].each { |value, domain| existent = !domain.empty? }

  existent
end


ac_3_look_ahead


# TODO: impl me
def arc_consistent()
  # returns true, when a value has been deleted from the variable Vi, hence true for triggering next revise
  # procedure REVISE(Vi,Vj)
  #   DELETE <- false;
  #   for each X in Di do
  #     if there is no such Y in Dj such that (X,Y) is consistent,
  #     then
  #       delete X from Di;
  #       DELETE <- true;
  #     endif;
  #   endfor;
  #   return DELETE;
  # end REVISE

  # procedure AC-3
  #   # Q = alle Variablentupel, die in einer Beziehung zueinander stehen (also einen Constraint erfüllen müssen)
  #   # Bsp: X * 2 = Z  und  Y < X, daraus ergibt sich Q = {(X,Z), (Z,X), (Y,X), (X,Y)}
  #   Q <- {(Vi,Vj) in arcs(G),i#j}; # d.h. i != j
  #   while not Q empty
  #     select and delete any arc (Vk,Vm) from Q;
  #     if REVISE(Vk,Vm) then
  #       Q <- Q union {(Vi,Vk) such that (Vi,Vk) in arcs(G),i#k,i#m}
  #     endif
  #   endwhile
  # end AC-3


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
end










puts "Ba-dum-ts!"
