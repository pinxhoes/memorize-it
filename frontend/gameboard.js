const startButton = document.getElementById('start-button');
const gameBoard = document.getElementById('game-board');

const API_URL = 'http://localhost:8080/api';

startButton.addEventListener('click', generateCards);

async function generateCards(){
    try {
        const response = await fetch(`${API_URL}/cards`)
        const cards = await response.json();

        renderGameBoard(cards);
    }catch (error){
        console.error('Error generating cards: ', error);
        alert('Error generating cards. Please try again.');
    }
}

function renderGameBoard(cards) {
    gameBoard.innerHTML = '';

    const sortedCards = [...cards].sort((a, b) => {
        if (a.col !== b.col) {
            return a.col - b.col;
        }
        return a.row - b.row;
    });

    sortedCards.forEach(card => {
        const cardElement = document.createElement('div');
        cardElement.classList.add('card');
        cardElement.dataset.id = card.id;
        cardElement.textContent = card.value;

        gameBoard.appendChild(cardElement);
    });
}