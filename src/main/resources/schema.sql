--Таблица экземпляров процессов
CREATE TABLE IF NOT EXISTS process_instance (
    id BIGSERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    initiator_ip VARCHAR(45) NOT NULL,
    input_data TEXT NOT NULL,
    result TEXT
);

--Таблица запущенных процессов перевода
CREATE TABLE IF NOT EXISTS process_translate_execution (
    id BIGSERIAL PRIMARY KEY,
    instance_id BIGINT NOT NULL REFERENCES process_instance(id) ON DELETE CASCADE
);

--Индекс для быстрого поиска по instance_id
CREATE INDEX IF NOT EXISTS idx_translate_execution_instance_id 
    ON process_translate_execution(instance_id);
