DO $$
    BEGIN
        FOR i IN 1..100 LOOP
                INSERT INTO classes (id, class_name, teacher_name, created_at, updated_at, is_deleted)
                VALUES (i, 'Class ' || i, 'Teacher ' || i, now(), now(), false);
            END LOOP;
    END $$;