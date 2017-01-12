``` ruby
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

# in ac3 packe ich alle constraint tuple rein (= FullList), für die es halt constraint gibt,
# also (farbe, nation), (farbe, drink) usw
# kopie von dieser liste machen, das dann Q
# aus Q erstes Elem rauslöschen und damit revise aufrufen (hier die constraints geprüft)
# wenn tuple gelöscht wurde aus Vk (zB Farbe), wird über FullList iteriert, geprüft,
# ob Farbe noch in anderen tuples enthalten ist zb (Zigarette, Farbe),
# wenn ja und diese tuples nicht in aktueller Q sind, werden sie wieder hinzugefügt
# so lange bis Q leer oder nicht konsistent. wenn Q leer und consistent, true zurück, Algo durch

# procedure AC-3
#   Q <- {(Vi,Vj) in arcs(G),i#j};
#   while not Q empty
#     select and delete any arc (Vk,Vm) from Q;
#     if REVISE(Vk,Vm) then
#       Q <- Q union {(Vi,Vk) such that (Vi,Vk) in arcs(G),i#k,i#m}
#     endif
#   endwhile
# end AC-3

# X = vars, D = domains, R1 = unary constraints, R2(x, y) = binary constraints on vars x and y
#  Output: Arc consistent domains for each variable.
# function ac3 (X, D, R1, R2)
# // Initial domains are made consistent with unary constraints.
#     for each x in X
#         D(x) := { vx in D(x) | R1(x) }
#     // 'worklist' contains all arcs we wish to prove consistent or not.
#     worklist := { (x, y) | there exists a relation R2(x, y) or a relation R2(y, x) }
#
#     do
#         select any arc (x, y) from worklist
#         worklist := worklist - (x, y)
#         if arc-reduce (x, y)
#             if D(x) is empty
#                 return failure
#             else
#                 worklist := worklist + { (z, x) | z != y and there exists
#                   a relation R2(x, z) or a relation R2(z, x) }
#     while worklist not empty
```
