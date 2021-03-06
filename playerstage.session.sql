
--- PRODUCTS

-- @block insert products
INSERT INTO products (
    `uuid`,
    `name`,
    `brand`,
    `is_pre_order`,
    `rate`,
    `description`,
    `model_name`
)
VALUES(
    UNHEX(REPLACE(UUID(),'-','')),
    'test_name',
    'test_brand',
    0,
    0,
    'test_description',
    'test_model_name'
)

-- @block delete all products
DELETE from products;

-- @block selct all products
SELECT * from products;

-- @block alert table
ALTER TABLE `products` MODIFY `ctime` DATETIME DEFAULT NOW();
ALTER TABLE `products` CHANGE COLUMN `rate` `rating` FLOAT DEFAULT(0);


-- @block show schema
SHOW COLUMNS FROM `products`

-- @block drop
DROP DATABASE `product_images`

-- @block delete
DELETE FROM `categories` WHERE `name`="testTransactional2"

-- @block delete test create usr
delete from `user` WHERE username = 'timmy'

-- @block drop constraint
ALTER TABLE `user_role_intermediary` DROP CONSTRAINT `fk_user_role_intermediary_user_id`
-- @block drop constraint
ALTER TABLE `refresh_token` DROP CONSTRAINT `fk_refresh_token_user_id`