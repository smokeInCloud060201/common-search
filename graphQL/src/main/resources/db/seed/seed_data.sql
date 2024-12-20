DO $$
    BEGIN
        FOR i IN 1..100 LOOP
                INSERT INTO classes (id, class_name, teacher_name, created_at, updated_at, is_deleted)
                VALUES (i, 'Class ' || i, 'Teacher ' || i, now(), now(), false);
            END LOOP;
    END $$;

INSERT INTO students (id, first_name , last_name , date_of_birth , gender , created_at, updated_at, is_deleted)
SELECT
    s as id,
    'Nguyen ' AS first_name,
    'Van ' || s AS last_name,
    '2005-01-01'::DATE + ((s % 365) * '1 day'::INTERVAL) AS date_of_birth,
    CASE WHEN (s % 2) = 0 THEN 'M' ELSE 'F' END AS gender,
    now() as created_at,
    now() as updated_at,
    false as is_deleted
FROM GENERATE_SERIES(1, 300000) s;

INSERT INTO classes_student (student_id , classes_id)
SELECT
    s AS student_id,
    (s % 100) + 1 AS classes_id -- Assign first class
FROM GENERATE_SERIES(1, 300000) s;

INSERT INTO classes_student (student_id , classes_id)
SELECT
    s AS student_id,
    ((s + 1) % 100) + 1 AS classes_id -- Assign second class
FROM GENERATE_SERIES(1, 300000) s;

INSERT INTO classes_student (student_id , classes_id)
SELECT
    s AS student_id,
    ((s + 2) % 100) + 1 AS classes_id -- Assign third class
FROM GENERATE_SERIES(1, 300000) s;
