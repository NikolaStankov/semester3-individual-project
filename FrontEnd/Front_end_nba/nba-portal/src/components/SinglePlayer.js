const SinglePlayer = (props) => {
  const { id, first_name, last_name, position } = props.player;
  const { team } = props.player;

  return (
    <tr>
      <td>{first_name}</td>
      <td>{last_name}</td>
      <td>{position}</td>
    </tr>
  );
};

export default SinglePlayer;
