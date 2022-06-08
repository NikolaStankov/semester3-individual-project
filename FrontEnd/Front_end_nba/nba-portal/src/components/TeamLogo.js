const TeamLogo = (props) => {
  const imgSrc = require("../images/logos/teamlogo_" + props.teamId + ".png");

  return (
    <div className="logo-container">
      <img src={imgSrc} alt="Logo" />
    </div>
  );
};

export default TeamLogo;
