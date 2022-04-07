import PlayersList from "../../components/PlayersList";
import useFetch from "../../custom-hooks/useFetch";

const Players = () => {
  const url = "http://localhost:8080/players";
  const { data: players } = useFetch(url);

  return <>{players && <PlayersList players={players} />}</>;
};

export default Players;
