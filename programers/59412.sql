select hour(DATETIME) HOUR, count(*) from ANIMAL_OUTS group by hour having HOUR >= 09 and HOUR <= 19 order by hour
