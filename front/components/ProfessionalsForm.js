"use client";
import React, { useState } from "react";

export default function ProfessionalForm({ professionalId = null }) {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    birthDate: "",
    gender: "",
    address: "",
    professionalType: "Professor", // Default: Professor
    disciplines: "",
    grades: "",
    workSchedule: "",
    academicFormation: "",
    specializations: "",
    areaOfExpertise: "",
    professionalExperience: "",
    phone: "",
    email: "",
    observations: "",
  });

  const [isEditing, setIsEditing] = useState(professionalId !== null);

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
      ? `https://api.example.com/professionals/${professionalId}`
      : "https://api.example.com/professionals";
    const method = isEditing ? "PUT" : "POST";

    fetch(endpoint, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        alert(isEditing ? "Profissional atualizado com sucesso!" : "Profissional adicionado com sucesso!");
      })
      .catch((error) => {
        console.error("Erro:", error);
        alert("Ocorreu um erro ao processar a solicitação.");
      });
  };

  return (
    <div className="container p-4">
      <h1 className="text-center mb-4">
        {isEditing ? "Editar Profissional" : "Adicionar Novo Profissional"}
      </h1>
      <form onSubmit={handleSubmit} className="professional-fomrs needs-validation">

        {/* Tipo de Profissional */}
        <fieldset className="mb-4">
          <legend>Tipo de Profissional</legend>
          <div className="row g-3">
            <div className="col-md-7">
              <select
                name="professionalType"
                value={formData.professionalType}
                onChange={handleChange}
                className="form-select"
                required
              >
                <option value="Professor">Professor</option>
                <option value="Coordenador">Coordenador</option>
              </select>
            </div>
          </div>
        </fieldset>

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

        {/* Informações Profissionais */}
        <fieldset className="mb-4">
          <legend>Informações Profissionais</legend>
          <div className="row g-3">
            {formData.professionalType === "Professor" ? (
              <>
                <div className="col-md-6">
                  <input
                    type="text"
                    name="disciplines"
                    placeholder="Disciplina(s) que Leciona"
                    value={formData.disciplines}
                    onChange={handleChange}
                    className="form-control"
                    required
                  />
                </div>
                <div className="col-md-6">
                  <input
                    type="text"
                    name="grades"
                    placeholder="Série(s)/Ano(s) que Atende"
                    value={formData.grades}
                    onChange={handleChange}
                    className="form-control"
                    required
                  />
                </div>
                <div className="col-12">
                  <input
                    type="text"
                    name="workSchedule"
                    placeholder="Horário de Trabalho"
                    value={formData.workSchedule}
                    onChange={handleChange}
                    className="form-control"
                  />
                </div>
              </>
            ) : (
              <>
                <div className="col-md-6">
                  <input
                    type="text"
                    name="areaOfExpertise"
                    placeholder="Área de Atuação"
                    value={formData.areaOfExpertise}
                    onChange={handleChange}
                    className="form-control"
                    required
                  />
                </div>
                <div className="col-md-6">
                  <input
                    type="number"
                    name="professionalExperience"
                    placeholder="Experiência Profissional (Anos)"
                    value={formData.professionalExperience}
                    onChange={handleChange}
                    className="form-control"
                  />
                </div>
              </>
            )}
            <div className="col-md-6">
              <input
                type="text"
                name="academicFormation"
                placeholder="Formação Acadêmica"
                value={formData.academicFormation}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-6">
              <input
                type="text"
                name="specializations"
                placeholder="Especializações"
                value={formData.specializations}
                onChange={handleChange}
                className="form-control"
              />
            </div>
          </div>
        </fieldset>

        {/* Contato */}
        <fieldset className="mb-4">
          <legend>Contato</legend>
          <div className="row g-3">
            <div className="col-md-6">
              <input
                type="text"
                name="phone"
                placeholder="Telefone"
                value={formData.phone}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
            <div className="col-md-6">
              <input
                type="email"
                name="email"
                placeholder="E-mail"
                value={formData.email}
                onChange={handleChange}
                className="form-control"
                required
              />
            </div>
          </div>
        </fieldset>

        {/* Observações */}
        <fieldset className="mb-4">
          <legend>Outros</legend>
          <div className="row g-3">
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
            {isEditing ? "Salvar Alterações" : "Adicionar Profissional"}
          </button>
        </div>
      </form>
    </div>
  );
}
