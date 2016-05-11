--Greedy exercise
------------------------------------COIN PROBLEM -----------------------------------------
-- Ejemplo coinProblem 63 [25,10,5,1]

--Un Ejemplo no optimo
--C= 25
--d 1 = 7, d 2 = 5, d 3 = 1.
coinProblem :: Int -> [Int] -> [Int]
coinProblem n [] = []
coinProblem n (x:xs) | n >= x = x : (coinProblem (n-x) (x:xs))
					 | otherwise = coinProblem n xs

---------------------------------PROBLEMA DE LAS CITAS -------------------------------------
--El problema de las citas que se solapan con prioridad.
--dado un conjunto de citas correspondientes a una fecha particular, encon-
--trar un subconjunto de tareas compatibles de prioridad maximal (o una aproximacion a esto).
--(sin solapamiento o sin repetidos en cada subconjunto)
--cada cita cuenta con una descripcion, una fecha, un horario
--de inicio, un horario de finalizacion y una prioridad

--				     (Descrip,Fecha,hIni,hFin,Prioridad)
--mainCitas :: Eq a =>[(String,String,Int,Int,Int)]->(String,String,Int,Int,Int)


--Generador de subconjuntos.
subconjuntoTareas :: [a] -> [[a]]
subconjuntoTareas [] = [[]]
subconjuntoTareas (x:xs)= [x:ys | ys <- subconjuntoTareas xs] ++ subconjuntoTareas xs

--El maximo subconjunto (que tiene mas elementos)
maximal :: [[a]] -> [a]
maximal [[]] = []
maximal [xs] = xs
maximal (xs:ys:xss) 	| length xs >= length ys  = maximal (xs:xss)
						| otherwise = maximal (ys:xss)

--Retorna la tupla con mas prioridad que se solapan en un misma hora de Inicio.
prioridad :: Ord a=>(a,a,a,a,a) -> (a,a,a,a,a) -> (a,a,a,a,a)
prioridad (d1,f1,h1,a1,p1) (d2,f2,h2,a2,p2) |  p1 >= p2 = (d1,f1,h1,a1,p1) 
										    | otherwise = (d2,f2,h2,a2,p2)
--Mismo horario de solapamiento
solapaHs:: Eq a =>(a,a,a,a,a)->(a,a,a,a,a)->Bool
solapaHs  (d1,f1,h1,a1,p1) (d2,f2,h2,a2,p2) | h1 == h2 = True
											|otherwise = False 
--Lista de tuplas filtra la que mas prioridad tiene si hay solapamiento de horas.
listPrioNoSolapa :: Ord a => [(a,a,a,a,a)]->[(a,a,a,a,a)]
listPrioNoSolapa [] = []
listPrioNoSolapa [(d1,fe1,hi1,hf1,pr1)] = [(d1,fe1,hi1,hf1,pr1)]
listPrioNoSolapa (((d1,fe1,hi1,hf1,pr1) :(d2,fe2,hi2,hf2,pr2): cs)) | solapaHs (d1,fe1,hi1,hf1,pr1) (d2,fe2,hi2,hf2,pr2)  = listPrioNoSolapa ( ( prioridad (d1,fe1,hi1,hf1,pr1) (d2,fe2,hi2,hf2,pr2) ) :cs)
																	| otherwise = (d2,fe2,hi2,hf2,pr2) : listPrioNoSolapa (( (d1,fe1,hi1,hf1,pr1) ):cs)

listListSolapa :: Ord a=> [[(a,a,a,a,a)]] -> [[(a,a,a,a,a)]]
listListSolapa [[]] = [[]]
listListSolapa ( cs :css ) = listPrioNoSolapa cs :  listListSolapa css

------------------------------- Main : Dada una lista de tuplas con : (Descrip,Fecha,hIni,hFin,Prioridad), 
--Filtra la lista de tuplas en las que no hay solapamiento de horas de inicio.
--El filtor de solapamiento lo realiza en base a la prioridad que tiene la cita.
mainCitas :: Ord a=> [(a,a,a,a,a)]->[(a,a,a,a,a)]
mainCitas []= []
mainCitas ( (d1,fe1,hi1,hf1,pr1): cs ) = 	maximal (listListSolapa (subconjuntoTareas ( (d1,fe1,hi1,hf1,pr1): cs ) ) )



---------------------------------EL PROBLEMAS DE INDIANA JONES CON GREEDY--------------------------







