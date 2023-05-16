import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { Container } from "@mui/system";
import { Button, Paper } from "@mui/material";
import { Grid } from "@mui/material";

export default function LibraryTextFields() {
  const paperStyle = {
    padding: "20px 20px",
    width: 600,
    margin: "auto",
    float: "left",
  };
  const paperButtonComponent = {
    padding: "20px 20px",
    width: 600,
    margin: "auto",
    float: "right",
  };
  const [isbn, setIsbn] = useState("");
  const [id, setId] = useState("");
  const [author, setAuthor] = useState("");
  const [year, setYear] = useState("");
  const [description, setDescription] = useState("");
  const [image, setImage] = useState("");
  const [addedBook, setAddedBook] = useState([]);

  const book = { id, isbn, author, year, description, image };

  const handleClickAdd = (event) => {
    event.preventDefault();
    fetch("http://localhost:8080/library/add", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(book),
    }).then(() => {
      console.log("New Book added");
    });
  };
  const handleClickEdit = () => {
    fetch(`http://localhost:8080/library/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(book),
    }).then(() => {
      console.log("Book Updated");
    });
  };
  useEffect(() => {
    fetch("http://localhost:8080/library/getAll")
      .then((res) => res.json())
      .then((result) => {
        setAddedBook(result);
      });
  }, []);

  return (
    <>
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
                required
                value={isbn}
                onChange={(event) => setIsbn(event.target.value)}
              />
            </Grid>
            <Grid spacing={3}>
              <TextField
                id="filled-basic"
                label="AUTHOR"
                variant="filled"
                fullWidth
                required
                value={author}
                onChange={(event) => setAuthor(event.target.value)}
              />
            </Grid>
            <Grid spacing={3}>
              <TextField
                id="filled-basic"
                label="YEAR"
                variant="filled"
                fullWidth
                required
                value={year}
                onChange={(event) => setYear(event.target.value)}
              />
            </Grid>
            <Grid spacing={3}>
              <TextField
                id="filled-basic"
                label="DESCRIPTION"
                variant="filled"
                fullWidth
                required
                value={description}
                onChange={(event) => setDescription(event.target.value)}
              />
            </Grid>
            <Grid spacing={3}>
              <TextField
                id="filled-basic"
                label="IMAGE"
                variant="filled"
                fullWidth
                value={image}
                onChange={(event) => setImage(event.target.value)}
              />
            </Grid>
            <br></br>
            <Container>
              <Grid rowSpacing={3}>
                <Button
                  variant="contained"
                  color="primary"
                  onClick={handleClickAdd}
                >
                  Submit Book
                </Button>
              </Grid>
            </Container>
          </Paper>
          <Paper elevation={3} style={paperStyle}>
            {addedBook.map((book) => {
              return (
                <Paper
                  elevation={6}
                  style={{ margin: "10px", padding: "15px", textAlign: "left" }}
                  key={book.id}
                >
                  Id:{book.id}
                  <br />
                  ISBN:{book.isbn}
                  <br />
                  Author:{book.author}
                  <br />
                  Year:{book.year}
                  <br />
                  Description:{book.description}
                  <br />
                  Image:{book.image}
                  {/* <Grid spacing={3}>
                    <img src={book.image} />
                  </Grid> */}
                </Paper>
              );
            })}
          </Paper>
        </Container>
      </Box>
      <Container>
        <Paper elevation={3} style={paperButtonComponent}>
          <Grid rowSpacing={3}>
            <TextField
              id="filled-basic"
              label="Book Id"
              variant="filled"
              fullWidth
              required
              value={id}
              onChange={(event) => setId(event.target.value)}
            />
            <p>Additional Action</p>
            <Button
              variant="contained"
              color="primary"
              onClick={handleClickEdit}
            >
              Edit book
            </Button>
          </Grid>
        </Paper>
      </Container>
    </>
  );
}
