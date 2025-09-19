import React, { useState } from "react";
import Navbar from "./NavBar";
import Profile from "./Profile";

const Dashboard: React.FC = () => {
  const [showProfile, setShowProfile] = useState(false);
  const [searchResults, setSearchResults] = useState<any[]>([]);

  const handleSearch = async (query: string) => {
    if (!query) {
      setSearchResults([]);
      return;
    }

    try {
      const response = await fetch(`http://localhost:8081/quiz/search?query=${query}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
      });

      const data = await response.json();
      setSearchResults(data); // assuming backend returns array of results
    } catch (error) {
      console.error("Search error:", error);
    }
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", height: "100vh" }}>
      <Navbar
        username={localStorage.getItem("username")}
        onProfileClick={() => setShowProfile(true)}
        onLogout={() => {
          localStorage.removeItem("token");
          window.location.reload();
        }}
        onSearch={handleSearch}
      />

      <main style={{ padding: "20px", flex: 1, overflowY: "auto" }}>
        {showProfile ? (
          <Profile onBack={() => setShowProfile(false)} />
        ) : (
          <div style={{
            backgroundColor: "#fff",
            padding: "40px",
            borderRadius: "10px",
            boxShadow: "0 4px 12px rgba(0,0,0,0.1)"
          }}>
            <h2>Quiz Dashboard</h2>
            {searchResults.length > 0 ? (
              <ul>
                {searchResults.map((quiz: any) => (
                  <li key={quiz.id}>{quiz.title}</li>
                ))}
              </ul>
            ) : (
              <p>Search for quizzes above...</p>
            )}
          </div>
        )}
      </main>
    </div>
  );
};

export default Dashboard;
