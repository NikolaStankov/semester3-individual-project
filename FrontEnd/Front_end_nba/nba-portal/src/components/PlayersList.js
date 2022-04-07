import SinglePlayer from "./SinglePlayer";

const PlayersList = (props) => {
  return (
    <ul>
      {props.players.map((player) => (
        <SinglePlayer key={player.id} player={player} />
      ))}
    </ul>
  );
};

export default PlayersList;
