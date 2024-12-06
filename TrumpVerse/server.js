const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const app = express();

app.use(cors({
  origin: 'http://localhost:3000', // Endre til din klient URL
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  allowedHeaders: ['Content-Type', 'Authorization'],
}));

app.use(bodyParser.json());

let merch = [
    
  {
    "id": 1,
    "name": "Trump T-shirt",
    "description": "A bold T-shirt featuring the Trump's christmas.",
    "price": 19.99,
    "image": "trump_tshirt.png"
  },
  {
    "id": 2,
    "name": "Trump Mug",
    "description": "A coffee mug with the iconic Trump face.",
    "price": 9.99,
    "image": "trump_mug.png"
  },
  {
    "id": 3,
    "name": "Trump Hat",
    "description": "A red hat with the \"Trump 2024\" slogan.",
    "price": 14.99,
    "image": "trump_hat.png"
  },
  {
    "id": 4,
    "name": "Trump Flag",
    "description": "A large flag featuring his return.",
    "price": 24.99,
    "image": "trump_flag.png"
  },
  {
    "id": 5,
    "name": "Trump Keychain",
    "description": "A keychain with Trump's favorite word.",
    "price": 4.99,
    "image": "trump_keychain.png"
  },
  {
    "id": 6,
    "name": "Trump Hoodie",
    "description": "A cozy hoodie with a bold Trump graphic on the front.",
    "price": 39.99,
    "image": "trump_hoodie.png"
  },
  {
    "id": 7,
    "name": "Trump Phone Case",
    "description": "A stylish phone case with Trump branding.",
    "price": 12.99,
    "image": "trump_phone_case.png"
  },
  {
    "id": 8,
    "name": "Trump Book",
    "description": "A book about Trump's rise to power and leadership strategies.",
    "price": 29.99,
    "image": "trump_book.png"
  }
];

// Hent alle merchandise
app.get('/api/TrumpMerchandise', (req, res) => {
  res.json(merch);
});

// Oppdater merchandise
app.put('/api/TrumpMerchandise/:id', (req, res) => {
  const { id } = req.params;
  const updatedItem = req.body;

  const index = merch.findIndex(item => item.id === parseInt(id));
  if (index !== -1) {
    merch[index] = { ...merch[index], ...updatedItem };
    res.json(merch[index]);
  } else {
    res.status(404).send('Item not found');
  }
});

app.listen(5236, () => {
  console.log('Server is running on port 5236');
});