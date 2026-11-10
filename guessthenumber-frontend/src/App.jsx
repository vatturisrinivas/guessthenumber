
import { useState, useEffect } from "react";
import "./App.css";

const API = "http://localhost:8080/api/game";

function App() {
  const [guess, setGuess] = useState("");
  const [attempts, setAttempts] = useState(0);
  const [gameOver, setGameOver] = useState(false);
  const [won, setWon] = useState(false);
  const [message, setMessage] = useState("Ready to play?");
  const [correctNumber, setCorrectNumber] = useState(null);
  const [loading, setLoading] = useState(false);

  const startGame = async () => {
    try {
      setLoading(true);

      const response = await fetch(`${API}/start`, {
        method: "POST"
      });

      if (!response.ok) throw new Error("Could not start game");

      const data = await response.json();

      updateGame(data);
      setGuess("");
    } catch (error) {
      setMessage("Cannot connect to server. Start Spring Boot!");
    } finally {
      setLoading(false);
    }
  };

  const updateGame = (data) => {
    setAttempts(data.attempts);
    setGameOver(data.gameOver);
    setWon(data.won);
    setMessage(data.message);
    setCorrectNumber(data.correctNumber);
  };

  const submitGuess = async () => {
    if (guess === "" || loading || gameOver) return;

    const number = Number(guess);

    if (!Number.isInteger(number) || number < 1 || number > 10) {
      setMessage("Enter a number between 1 and 10!");
      return;
    }

    try {
      setLoading(true);

      const response = await fetch(
        `${API}/guess?guess=${number}`,
        { method: "POST" }
      );

      if (!response.ok) throw new Error("Guess failed");

      const data = await response.json();

      updateGame(data);
      setGuess("");
    } catch (error) {
      setMessage("Something went wrong. Check your backend.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    startGame();
  }, []);

  return (
    <div className="page">
      <div className="game-card">

        <div className="game-icon">🎯</div>

        <h1>Guess The Number</h1>

        <p className="subtitle">
          I'm thinking of a number between 1 and 10.
          Can you guess it?
        </p>

        <div className="attempts">
          <span>ATTEMPTS</span>
          <strong>{attempts} / 3</strong>
        </div>

        <div className="hearts">
          {[1, 2, 3].map((item) => (
            <span key={item}>
              {item > attempts ? "❤️" : "🖤"}
            </span>
          ))}
        </div>

        <input
          type="number"
          min="1"
          max="10"
          placeholder="Enter your guess"
          value={guess}
          onChange={(e) => setGuess(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && submitGuess()}
          disabled={gameOver || loading}
        />

        <button
          className="guess-btn"
          onClick={submitGuess}
          disabled={gameOver || loading || !guess}
        >
          {loading ? "Please wait..." : "SUBMIT GUESS"}
        </button>

        <div className={`message ${won ? "success" : gameOver ? "failure" : ""}`}>
          {message}
        </div>

        {gameOver && (
          <div className="result">
            {won ? "🥳 You did it!" : "The number was " + correctNumber}
          </div>
        )}

        <button className="restart-btn" onClick={startGame} disabled={loading}>
          ↻ PLAY AGAIN
        </button>

      </div>
    </div>
  );
}

export default App;
