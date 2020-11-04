WITH RECURSIVE HOUR AS (
    SELECT 0 UNIT_HOUR 
    UNION ALL 
    SELECT UNIT_HOUR+1 
    FROM HOUR 
    WHERE UNIT_HOUR < 23
)
SELECT HOUR.UNIT_HOUR AS HOUR, COALESCE(COUNT(ANIMAL_ID), 0) COUNT 
FROM HOUR 
LEFT JOIN ANIMAL_OUTS 
ON HOUR.UNIT_HOUR = HOUR(DATETIME)
GROUP BY HOUR.UNIT_HOUR