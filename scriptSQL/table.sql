
CREATE Table web_users(
    id_user INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_user)
);

CREATE Table web_credits(
    id_credit INT NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(100) NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    date_debut DATE,
    date_fin DATE,
    PRIMARY KEY (id_credit)
);

CREATE Table web_depenses(
    id_depense INT NOT NULL AUTO_INCREMENT,
    id_credit INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    date_depense DATE DEFAULT CURRENT_TIME,
    PRIMARY  KEY (id_depense),
    Foreign Key (id_credit) REFERENCES web_credits(id_credit)
);