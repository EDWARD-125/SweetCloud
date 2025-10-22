-- Insertar roles iniciales si no existen
INSERT INTO roles (nombre) VALUES ('ROLE_USER') ON CONFLICT (nombre) DO NOTHING;
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN') ON CONFLICT (nombre) DO NOTHING;

-- Usuario de prueba admin/admin123
-- La contraseña "admin123" está hasheada con BCrypt
INSERT INTO usuarios (username, password) 
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy')
ON CONFLICT (username) DO NOTHING;

-- Asignar rol ADMIN al usuario admin
INSERT INTO usuario_roles (usuario_id, rol_id)
SELECT u.id, r.id FROM usuarios u, roles r
WHERE u.username = 'admin' AND r.nombre = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;