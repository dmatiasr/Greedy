--Greedy exercise
--Coin Problem


-- Ejemplo coinProblem 63 [25,10,5,1]

--Un Ejemplo no optimo
--C= 25
--d 1 = 7, d 2 = 5, d 3 = 1.



coinProblem :: Int -> [Int] -> [Int]
coinProblem n [] = []
coinProblem n (x:xs) | n >= x = x : (coinProblem (n-x) (x:xs))
					 | otherwise = coinProblem n xs
