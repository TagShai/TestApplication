INSERT INTO `users` (`id`, `username`, `password`) VALUES
    (1, 'user1', 'password1'),
    (2, 'user2', 'password2'),
    (3, 'user3', 'password3');

INSERT INTO `genres` (`id`, `name`) VALUES
    (1, 'comedy'),
    (2, 'fantastic'),
    (3, 'adventure'),
    (4, 'historical'),
    (5, 'westerns');

INSERT INTO `films` (`id`, `name`, `description`, `country`, `quality`) VALUES
    (1, 'Guardians of the Galaxy', 'A brave traveler Peter Quill gets into the hands of a mysterious artifact belonging to a powerful and ruthless villain Ronan, who is building insidious plans to capture the universe. Peter is at the center of intergalactic hunting, where the victim is himself. The only way to save your life is to unite with four unsociable rogues: a warrior raccoon named Rocket, an anthropoid Gruth tree, a deadly Gamora and a revenge-hungry Drax, also known as the Destroyer.', 'USA', 'BDRip'),
    (2, 'Indiana Jones: The Raiders of the Lost Ark', 'A well-known archaeologist and specialist in the occult sciences, Dr. Jones receives a dangerous task from the US government. He must go in search of a unique relic - the sacred Ark. But Indiana and does not suspect that a similar order has already received secret agents', 'Russia', 'TS'),
    (3, 'Train to Hume', 'After the arrest of the famous bandit Ben Wade, his gang continues to threaten the local people. A veteran of the Civil War and the owner of a small ranch, Dan Evans agrees to secretly deliver Wade to the nearest city with a railway station to be taken by train to Fort Yuma. As soon as they stop at the hotel, it becomes obvious that the secret is revealed, and for both the hunt begins ...', 'USA', 'WEB-DLRip');

INSERT INTO `film_genres` (`film_id`, `genre_id`) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 3),
    (2, 4),
    (3, 5);