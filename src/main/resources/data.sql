-- Insert Permissions
INSERT INTO permissions (id, name, description) VALUES
                                                    (1, 'READ_USERS', 'Permission to read user data'),
                                                    (2, 'WRITE_USERS', 'Permission to create/update users'),
                                                    (3, 'DELETE_USERS', 'Permission to delete users'),
                                                    (4, 'READ_ROLES', 'Permission to read roles'),
                                                    (5, 'MANAGE_ROLES', 'Permission to manage roles'),
                                                    (6, 'READ_PERMISSIONS', 'Permission to read permissions'),
                                                    (7, 'MANAGE_PERMISSIONS', 'Permission to manage permissions');

-- Insert Roles
INSERT INTO roles (id, name, description) VALUES
                                              (1, 'USER', 'Standard user role with basic access'),
                                              (2, 'ADMIN', 'Administrator role with full access'),
                                              (3, 'MODERATOR', 'Moderator role with limited admin access');

-- Assign Permissions to Roles
INSERT INTO role_permissions (role_id, permission_id) VALUES
-- USER permissions
(1, 1), -- USER has READ_USERS

-- ADMIN permissions (all)
(2, 1), -- ADMIN has READ_USERS
(2, 2), -- ADMIN has WRITE_USERS
(2, 3), -- ADMIN has DELETE_USERS
(2, 4), -- ADMIN has READ_ROLES
(2, 5), -- ADMIN has MANAGE_ROLES
(2, 6), -- ADMIN has READ_PERMISSIONS
(2, 7), -- ADMIN has MANAGE_PERMISSIONS

-- MODERATOR permissions
(3, 1), -- MODERATOR has READ_USERS
(3, 2), -- MODERATOR has WRITE_USERS
(3, 4); -- MODERATOR has READ_ROLES

-- Insert Users
-- Password: password123 (BCrypt encoded)
-- Note: You need to generate BCrypt hash for your passwords
-- For testing, use: password123 -> $2a$10$N8nQPvHt.HqhAKxVYXYvYeqCJqHqQkQ4J.5U8wVc0PnPvYz5Xd3j6

INSERT INTO users (id, username, email, password, first_name, last_name, phone_number, enabled, account_non_expired, account_non_locked, credentials_non_expired, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                                                                                                                                      (1, 'admin', 'admin@gmail.com', '$2a$10$N8nQPvHt.HqhAKxVYXYvYeqCJqHqQkQ4J.5U8wVc0PnPvYz5Xd3j6', 'Admin', 'User', '1234567890', true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
                                                                                                                                                                                                                      (2, 'Nitesh', 'Nitesh@gmail.com', '$2a$10$N8nQPvHt.HqhAKxVYXYvYeqCJqHqQkQ4J.5U8wVc0PnPvYz5Xd3j6', 'Nitesh', 'Singh', '0987654321', true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
                                                                                                                                                                                                                      (3, 'Ram', 'Ram@gmail.com', '$2a$10$N8nQPvHt.HqhAKxVYXYvYeqCJqHqQkQ4J.5U8wVc0PnPvYz5Xd3j6', 'Ram', 'Singh', '5555555555', true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system'),
                                                                                                                                                                                                                      (4, 'Piyush', 'Piyush@gmail.com', '$2a$10$N8nQPvHt.HqhAKxVYXYvYeqCJqHqQkQ4J.5U8wVc0PnPvYz5Xd3j6', 'Piyush', 'Singh', '4444444444', true, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- Assign Roles to Users
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 2), -- admin has ADMIN role
                                              (1, 1), -- admin also has USER role
                                              (2, 1), -- Nitesh has USER role
                                              (3, 3), -- Ram has MODERATOR role
                                              (4, 1); -- Piyush has USER role