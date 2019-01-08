create table dashboard_role_res
(
  role_res_id bigint auto_increment
    primary key,
  role_id     varchar(100) null,
  res_type    varchar(100) null,
  res_id      bigint       null,
  permission  varchar(20)  null
);
create table dashboard_board
(
  board_id    bigint auto_increment
    primary key,
  user_id     varchar(50)                         not null,
  category_id bigint                              null,
  board_name  varchar(100)                        not null,
  layout_json text                                null,
  create_time timestamp default CURRENT_TIMESTAMP not null,
  update_time timestamp                           null
);
create table dashboard_widget
(
  widget_id     bigint auto_increment
    primary key,
  user_id       varchar(100)                        not null,
  category_name varchar(100)                        null,
  widget_name   varchar(100)                        null,
  data_json     text                                null,
  create_time   timestamp default CURRENT_TIMESTAMP not null,
  update_time   timestamp                           null
);
create table dashboard_datasource
(
  datasource_id bigint auto_increment
    primary key,
  user_id       varchar(50)                         not null,
  source_name   varchar(100)                        not null,
  source_type   varchar(100)                        not null,
  config        text                                null,
  create_time   timestamp default CURRENT_TIMESTAMP not null,
  update_time   timestamp                           null
);
create table dashboard_category
(
  category_id   bigint auto_increment
    primary key,
  category_name varchar(100) not null,
  user_id       varchar(100) not null
);
create table dashboard_dataset
(
  dataset_id    bigint auto_increment
    primary key,
  user_id       varchar(100)                        not null,
  category_name varchar(100)                        null,
  dataset_name  varchar(100)                        null,
  data_json     text                                null,
  create_time   timestamp default CURRENT_TIMESTAMP not null,
  update_time   timestamp                           null
);
create table dashboard_user_role
(
  user_role_id bigint auto_increment
    primary key,
  user_id      varchar(100) null,
  role_id      varchar(100) null
);
create table dashboard_job
(
  job_id         bigint auto_increment
    primary key,
  job_name       varchar(200) null,
  cron_exp       varchar(200) null,
  start_date     timestamp    null,
  end_date       timestamp    null,
  job_type       varchar(200) null,
  job_config     text         null,
  user_id        varchar(100) null,
  last_exec_time timestamp    null,
  job_status     bigint       null,
  exec_log       text         null
);
create table dashboard_user
(
  user_id       varchar(50)  not null
    primary key,
  login_name    varchar(100) null,
  user_name     varchar(100) null,
  user_password varchar(100) null,
  user_status   varchar(100) null
);
create table dashboard_board_param
(
  board_param_id bigint auto_increment
    primary key,
  user_id        varchar(50) not null,
  board_id       bigint      not null,
  config         text        null
);