DROP TABLE purchase, game_player, death, player, game;

CREATE TABLE player
(
    player_uuid VARCHAR(36) PRIMARY KEY,
    player_name VARCHAR(255) NOT NULL
);

CREATE TABLE game
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE game_player
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    game_id     INT         NOT NULL,
    player_uuid VARCHAR(36) NOT NULL,
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (player_uuid) REFERENCES player (player_uuid)
);

CREATE TABLE death
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    game_id         INT         NOT NULL,
    murderer_uuid   VARCHAR(36) NOT NULL,
    dead_uuid       VARCHAR(36) NOT NULL,
    murderer_reward INT         NOT NULL,
    dead_reward     INT         NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (murderer_uuid) REFERENCES player (player_uuid),
    FOREIGN KEY (dead_uuid) REFERENCES player (player_uuid)
);

CREATE TABLE purchase
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    game_id     INT          NOT NULL,
    player_uuid VARCHAR(36)  NOT NULL,
    item        VARCHAR(255) NOT NULL,
    price       INT          NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (player_uuid) REFERENCES player (player_uuid)
);