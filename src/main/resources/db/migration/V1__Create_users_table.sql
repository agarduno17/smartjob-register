CREATE TABLE users (
    id UUID NOT NULL,  -- Usamos UUID como clave primaria
    name VARCHAR(100) NOT NULL,  -- El nombre del usuario, no puede ser nulo
    username VARCHAR(255) NOT NULL,  -- El correo electrónico del usuario, no puede ser nulo y debe ser único
    password VARCHAR(255) NOT NULL,  -- La contraseña encriptada del usuario, no puede ser nula
    created_at TIMESTAMP NOT NULL,  -- Fecha de creación del usuario
    modified_at TIMESTAMP NOT NULL,  -- Fecha de la última modificación
    last_login TIMESTAMP NOT NULL,  -- Fecha del último ingreso, inicialmente la misma que la de creación
    token VARCHAR(255) NOT NULL,  -- El token de acceso del usuario
    is_active BOOLEAN NOT NULL DEFAULT TRUE,  -- Indica si el usuario está activo o no
    PRIMARY KEY (id),  -- Definimos la clave primaria como UUID
    UNIQUE (username, token)  -- El correo electrónico debe ser único al igual que el token
);

-- Tabla para los teléfonos asociados a cada usuario
CREATE TABLE phones (
    id UUID NOT NULL,  -- UUID como clave primaria
    user_id UUID NOT NULL,  -- Relación con la tabla usuarios
    number VARCHAR(20) NOT NULL,  -- Número de teléfono
    city_code VARCHAR(10) NOT NULL,  -- Código de ciudad
    country_code VARCHAR(10) NOT NULL,  -- Código de país
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),  -- Clave primaria UUID
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- Relación con usuarios
);

-- Crear índice en el campo email para búsquedas rápidas y validación única
CREATE INDEX idx_user_username ON users(username);