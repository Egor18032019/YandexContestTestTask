 
SELECT  date ,description,statistic.name,categories.name AS category_name ,brands.name AS brands_name 
FROM statistic
INNER JOIN categories ON statistic.category_id =categories.id
 INNER JOIN brands  ON statistic.brand_id  =brands .id
 
 