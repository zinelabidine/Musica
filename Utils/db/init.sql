-------------------------------------------------------------------------------
-------------      Script init database Musica          -----------------------
-------------------------------------------------------------------------------

-- Table Client
insert into Client Values(
  1,
  'zhadjcadi@gmail.com',
  'zhadjcadi',
  'mdp',
  'HADJ CADI',
  '2349498483928311',
  'Zine El Abidine',
  '0751569308'
);

-- Table Categorie
insert into Categorie values(
  1,
  'Guitare'
);

-- Table Marque
insert into Marque values(
  1,
  'Fender'
);

-- Table Instrument
insert into Instrument values(
  1,
  239,
  100,
  'CD-60 CE BK',
  1,
  1
);

-- Table Profil
insert into Profil values(
  1,
  'Super administrateur'
);

-- Table Administrateur
insert into Administrateur values(
  1,
  'youness.hardi@gmail.com',
  'yhardi',
  'helloworld',
  'HARDI',
  'Youness',
  '0761945743',
  1
);
