SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_OUTS O
INNER JOIN ANIMAL_INS I
WHERE O.ANIMAL_ID = I.ANIMAL_ID
ORDER BY DATEDIFF(O.DATETIME, I.DATETIME) DESC
LIMIT 2
