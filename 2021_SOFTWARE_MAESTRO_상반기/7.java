select O.buyer_id, O.buy_date, L.book_name, L.price
from orderInfo as O
left join library as L using(book_id)
where L.book_name = 'Looking with Elice' or (O.buy_date between '2020-07-27' and '2020-07-31')
order by O.buy_date;
