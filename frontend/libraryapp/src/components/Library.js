import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { Container } from "@mui/system";
import { Paper } from "@mui/material";
import { Grid } from "@mui/material";

export default function LibraryTextFields() {
  const paperStyle = { padding: "20px 20px", width: 600, margin: " auto" };
  const [isbn, setIsbn] = useState("");
  const [author, setAuthor] = useState("");
  const [year, setYear] = useState("");
  const [description, setDescription] = useState("");
  const [image, setImage] = useState("");

  return (
    <Box
      component="form"
      sx={{
        "& > :not(style)": { m: 1, width: "25ch" },
      }}
      noValidate
      autoComplete="off"
    >
      <Container>
        <Paper elevation={3} style={paperStyle}>
          <p>ADD BOOK</p>
          <Grid spacing={3}>
            <TextField
              id="filled-basic"
              label="ISBN"
              variant="filled"
              fullWidth
            />
          </Grid>
          <Grid spacing={3}>
            <TextField
              id="filled-basic"
              label="AUTHOR"
              variant="filled"
              fullWidth
            />
          </Grid>
          <Grid spacing={3}>
            <TextField
              id="filled-basic"
              label="YEAR"
              variant="filled"
              fullWidth
            />
          </Grid>
          <Grid spacing={3}>
            <TextField
              id="filled-basic"
              label="DESCRIPTION"
              variant="filled"
              fullWidth
            />
          </Grid>
          <Grid spacing={3}>
            <TextField
              id="filled-basic"
              label="IMAGE"
              variant="filled"
              fullWidth
            />
          </Grid>
        </Paper>
      </Container>
    </Box>
  );
}
