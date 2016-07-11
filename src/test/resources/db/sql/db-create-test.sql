CREATE TABLE `person` (
  `id`             BIGINT(20)             AUTO_INCREMENT      PRIMARY KEY,
  `first_name`     VARCHAR(100),
  `last_name`      VARCHAR(100),
  `middle_name`    VARCHAR(100),
  `birthday`       TIMESTAMP
);
