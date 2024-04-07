CREATE TABLE IF NOT EXISTS tasks
(
    id              uuid            PRIMARY KEY,
    name            varchar(64)     NOT NULL,
    description     varchar(512),
    priority        varchar(64)     NOT NULL,
    status          varchar(64)     NOT NULL,
    created_at      timestamp       NOT NULL,
    deadline        timestamp
);
