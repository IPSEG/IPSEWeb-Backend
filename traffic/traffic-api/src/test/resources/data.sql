
INSERT INTO TB_BUS_STOP (bus_stop_id, bus_stop_name, information_collect_date, city, detail_city, city_code, mobile_code, longitude, latitude)
VALUES
    ('ADB354000076', '신덕1리', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540076', '128.83092600', '36.52432500'),
    ('ADB354000077', '나천', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540077', '128.87639000', '36.48444400'),
    ('ADB354000078', '골삽실', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540078', '128.88760600', '36.46224800'),
    ('ADB354000079', '금곡(길안)', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540079', '128.93328100', '36.39436000'),
    ('ADB354000080', '대사.토일', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540080', '128.94035300', '36.36858000'),
    ('ADB354000081', '마사리', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540081', '128.90827700', '36.30243000'),
    ('ADB354000082', '백자리', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540082', '128.86966900', '36.41079200'),
    ('ADB354000083', '송사', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540083', '128.92804800', '36.36600600'),
    ('ADB354000084', '오락', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540084', '128.91400100', '36.43337200'),
    ('ADB354000085', '유곡', '2023-10-16 00:00:00.000000'::timestamp, '안동', '경상북도 안동시', '37040', '540085', '128.92680300', '36.33090300') ON CONFLICT DO NOTHING;



INSERT INTO TB_CARD_GROUP(card_group_id, created_date, last_modified_date, name, create_by, last_modified_by) VALUES(1, now(), now(), 'default', 'root', 'root');