% domains

%% +Vars ins +Domain
%
%  The variables in the list Vars are elements of Domain.

% Vs ins D :-
%   must_be(list, Vs), % ist drin
%   maplist(fd_variable, Vs), % ist drin
%   drep_to_domain(D, Dom),
%   domains(Vs, Dom).

% sets domain for every variable in attribute list (equals house numbers)
assignHouseNumbers([], _ResultList) :- !.
assignHouseNumbers([First | Rest], ResultList) :-
  First = [1, 2, 3, 4, 5],
  assignHouseNumbers(Rest, TempList),
  append([First], TempList, ResultList).

% for deleting a value from domain for a specific var:
% delete([1,2,3,4,5], 5, Reso).

%
% solve :-
%   % Houses = [1, 2, 3, 4, 5],
%   Colours = [Red, Green, White, Yellow, Blue],
%   Nationalities = [Briton, Swede, Dane, German, Norwegian],
%   Pets = [Dog, Bird, Cat, Horse, Fish],
%   Beverages = [Tea, Coffee, Milk, Beer, Water],
%   Cigarettes = [PallMall, Dunhill, Malboro, Winfield, Rothmanns],
%   HouseValues = [Colours, Nationalities, Pets, Drinks, Cigarettes],


  % colours = [red, green, white, yellow, blue].
  % nationalities = [briton, swede, dane, german, norwegian].
  % pets = [dog, bird, cat, horse, fish].
  % beverages = [tea, coffee, milk, beer, water].
  % cigarettes = [pall_mall, dunhill, malboro, winfield, rothmanns].

  % TODO: impl me
  % def arc_consistent()
    % returns true, when a value has been deleted from the variable Vi, hence true for triggering next revise
    % procedure REVISE(Vi,Vj)
    %   DELETE <- false;
    %   for each X in Di do
    %     if there is no such Y in Dj such that (X,Y) is consistent,
    %     then
    %       delete X from Di;
    %       DELETE <- true;
    %     endif;
    %   endfor;
    %   return DELETE;
    % end REVISE

    % procedure AC-3
    %  % Q = alle Variablentupel, die in einer Beziehung zueinander stehen (also einen Constraint erfüllen müssen)
    %  % Bsp: X * 2 = Z  und  Y < X, daraus ergibt sich Q = {(X,Z), (Z,X), (Y,X), (X,Y)}
    %  Q <- {(Vi,Vj) in arcs(G),i#j}; # d.h. i != j
    %  while not Q empty
      %  select and delete any arc (Vk,Vm) from Q;
      %  if REVISE(Vk,Vm) then
        %  Q <- Q union {(Vi,Vk) such that (Vi,Vk) in arcs(G),i#k,i#m}
      %  endif
    %  endwhile
    % end AC-3


    % AC3 LOOKAHEAD => TODO!
    % Vcv = next variable, procedure returns true when there are values for this variable which are consistent
    % (and only those values are afterwards still existent within the variable's domain)
      % procedure AC3-LA(cv)
    %  Q <- {(Vi,Vcv) in arcs(G),i>cv}; # !!!!! i>cv !!!!! nur für folgende Variablen testen
    %  consistent <- true;
      %  while not Q empty & consistent
      %  select and delete any arc (Vk,Vm) from Q;
      %  if REVISE(Vk,Vm) then
        %  Q <- Q union {(Vi,Vk) such that (Vi,Vk) in arcs(G),i#k,i#m,i>cv}
        %  consistent <- not Dk empty
      %  endif
    %  endwhile
      %  return consistent
    % end AC3-LA
  % end
