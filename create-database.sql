DROP TABLE Purchase, GamePlayer, Death, Player, Game;

CREATE TABLE Player (
                        playerUuid VARCHAR(36) PRIMARY KEY,
                        playerName VARCHAR(255) NOT NULL
);

CREATE TABLE Game (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE GamePlayer (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            gameId INT NOT NULL,
                            playerUuid VARCHAR(36) NOT NULL,
                            FOREIGN KEY (gameId) REFERENCES Game(id),
                            FOREIGN KEY (playerUuid) REFERENCES Player(playerUuid)
);

CREATE TABLE Death (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       gameId INT NOT NULL,
                       murdererUuid VARCHAR(36) NOT NULL,
                       deadUuid VARCHAR(36) NOT NULL,
                       murdererReward INT NOT NULL,
                       deadReward INT NOT NULL,
                       createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (gameId) REFERENCES Game(id),
                       FOREIGN KEY (murdererUuid) REFERENCES Player(playerUuid),
                       FOREIGN KEY (deadUuid) REFERENCES Player(playerUuid)
);

CREATE TABLE Purchase (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          gameId INT NOT NULL,
                          playerUuid VARCHAR(36) NOT NULL,
                          item VARCHAR(255) NOT NULL,
                          price INT NOT NULL,
                          createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (gameId) REFERENCES Game(id),
                          FOREIGN KEY (playerUuid) REFERENCES Player(playerUuid)
);