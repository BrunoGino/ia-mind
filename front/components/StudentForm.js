"use client";
import React, { useState } from "react";

export default function StudentForm({ studentId = null }) {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    birthDate: "",
    gender: "",
    address: "",
    school: "",
    grade: "",
    classGroup: "",
    shift: "",
    medications: "",
    allergies: "",
    healthHistory: "",
    diagnoses: [],
    medicalReports: null,
    guardianName: "",
    guardianPhone: "",
    guardianEmail: "",
    observations: "",
  });

  const [isEditing, setIsEditing] = useState(studentId !== null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const endpoint = isEditing
      ? `https://api.example.com/students/${studentId}`
      : "https://api.example.com/students";
    const method = isEditing ? "PUT" : "POST";

    fetch(endpoint, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        alert(isEditing ? "Aluno atualizado com sucesso!" : "Aluno adicionado com sucesso!");
      })
      .catch((error) => {
        console.error("Erro:", error);
        alert("Ocorreu um erro ao processar a solicitação.");
      });
  };

  return (
    <div className="container p-4">
      <h1 className="text-center mb-4">
        {isEditing ? "Editar Aluno" : "Adicionar Novo Aluno"}
      </h1>
      <form onSubmit={handleSubmit} className="needs-validation students-fomrs">
        {/* Dados Pessoais */}
        <fieldset className="mb-4">
          <legend>Dados Pessoais</legend>
          <div className="row g-3">
            <div className="col-md-6">
              <input
                type="text"
                name="firstName"
                placeholder="Nome"
                value={formData.firstName}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-6">
              <input
                type="text"
                name="lastName"
                placeholder="Sobrenome"
                value={formData.lastName}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-6">
              <input
                type="text"
                name="gender"
                placeholder="Gênero"
                value={formData.gender}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-6">
              <input
                type="date"
                name="birthDate"
                value={formData.birthDate}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-12">
              <input
                type="text"
                name="address"
                placeholder="Endereço Completo"
                value={formData.address}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
          </div>
        </fieldset>

        {/* Dados Escolares */}
        <fieldset className="mb-4">
          <legend>Dados Escolares</legend>
          <div className="row g-3">
            <div className="col-md-6">
              <input
                type="text"
                name="school"
                placeholder="Escola"
                value={formData.school}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-3">
              <input
                type="text"
                name="grade"
                placeholder="Série"
                value={formData.grade}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-3">
              <input
                type="text"
                name="classGroup"
                placeholder="Turma"
                value={formData.classGroup}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="offset-6 col-md-6 col">
              <select
                name="shift"
                value={formData.shift}
                onChange={handleChange}
                className="form-select"
                required
              >
                <option value="">Turno</option>
                <option value="Manhã">Manhã</option>
                <option value="Tarde">Tarde</option>
                <option value="Noite">Noite</option>
              </select>
            </div>
          </div>
        </fieldset>

        {/* Dados de Saúde */}
        <fieldset className="mb-4">
          <legend>Dados de Saúde</legend>
          <div className="row g-3">
            <div className="col-md-6">
              <input
                type="text"
                name="medications"
                placeholder="Medicamentos em Uso"
                value={formData.medications}
                onChange={handleChange}
                className="form-control"
              />
            </div>
            <div className="col-md-6">
              <input
                type="text"
                name="allergies"
                placeholder="Alergias"
                value={formData.allergies}
                onChange={handleChange}
                className="form-control"
              />
            </div>
            <div className="col-12">
              <textarea
                name="healthHistory"
                placeholder="Histórico de Saúde Mental"
                value={formData.healthHistory}
                onChange={handleChange}
                className="form-control"
                rows="3"
              ></textarea>
            </div>
            <div className="col-12">
              <input
                type="file"
                name="medicalReports"
                onChange={(e) =>
                  setFormData({ ...formData, medicalReports: e.target.files[0] })
                }
                className="form-control"
              />
            </div>
          </div>
        </fieldset>

        {/* Dados do Responsável */}
        <fieldset className="mb-4">
          <legend>Dados do Responsável</legend>
          <div className="row g-3">
            <div className="col-md-6">
              <input
                type="text"
                name="guardianName"
                placeholder="Nome do Responsável"
                value={formData.guardianName}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-6">
              <input
                type="text"
                name="guardianPhone"
                placeholder="Telefone"
                value={formData.guardianPhone}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-12">
              <input
                type="email"
                name="guardianEmail"
                placeholder="E-mail do Responsável"
                value={formData.guardianEmail}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-12">
              <textarea
                name="observations"
                placeholder="Observações Relevantes"
                value={formData.observations}
                onChange={handleChange}
                className="form-control"
                rows="3"
              ></textarea>
            </div>
          </div>
        </fieldset>

        <div className="text-center">
          <button type="submit" className="techwave_fn_button">
            {isEditing ? "Salvar Alterações" : "Adicionar Aluno"}
          </button>
        </div>
      </form>
    </div>
  );
}