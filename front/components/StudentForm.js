"use client";
import { useRouter } from "next/router";
import React, { useEffect, useState } from "react";

export default function StudentForm() {
  const [isClient, setIsClient] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [studentId, setStudentId] = useState(null);
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    birthDate: "",
    phone: "",
    gender: "",
    email: "",
    address: "",
    school: "",
    grade: "",
    classGroup: "",
    shift: "",
    medications: "",
    allergies: "",
    healthHistory: "",
    diagnoses: "",
    medicalReports: null,
    guardianName: "",
    guardianPhone: "",
    guardianEmail: "",
    observations: "",
  });

  useEffect(() => {
    // Garante que o código será executado no lado do cliente
    setIsClient(true);

    if (typeof window !== "undefined") {
      const router = new URLSearchParams(window.location.search);
      const id = router.get("id");

      if (id) {
        setStudentId(id);
        setIsEditing(true);
      }
    }
  }, []);

  // Carregar dados do estudante para edição
  useEffect(() => {
    if (isEditing && studentId) {
      fetch(
        `http://iamind-alb-1060024196.eu-west-1.elb.amazonaws.com/api/users/students/${studentId}`
      )
        .then((response) => {
          if (!response.ok) {
            throw new Error("Erro ao buscar os dados do aluno.");
          }
          return response.json();
        })
        .then((data) => {
          setFormData({
            firstName: data.firstName || "",
            lastName: data.lastName || "",
            birthDate: data.dateOfBirth || "",
            phone: data.phone || "",
            gender: data.gender || "",
            email: data.email || "",
            address: data.fullAddress || "",
            school: data.school || "",
            grade: data.schoolYear || "",
            classGroup: data.classRoom || "",
            shift: data.shift || "",
            medications: data.medicationsInUse || "",
            allergies: data.allergies || "",
            healthHistory: data.mentalHealthHistory || "",
            diagnoses: data.previousDiagnoses || "",
            medicalReports: null,
            guardianName: data.guardianName || "",
            guardianPhone: data.guardianPhone || "",
            guardianEmail: data.guardianEmail || "",
            observations: "",
          });
        })
        .catch((error) => {
          console.error("Erro ao carregar os dados do aluno:", error);
          alert("Erro ao carregar os dados do aluno.");
        });
    }
  }, [isEditing, studentId]);

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
      ? `http://iamind-alb-1060024196.eu-west-1.elb.amazonaws.com/api/users/students/${studentId}`
      : "http://iamind-alb-1060024196.eu-west-1.elb.amazonaws.com/api/users/students";
    const method = isEditing ? "PUT" : "POST";

    const payload = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      dateOfBirth: formData.birthDate,
      phone: formData.phone,
      gender: formData.gender,
      email: formData.email,
      fullAddress: formData.address,
      school: formData.school,
      schoolYear: formData.grade,
      classRoom: formData.classGroup,
      shift: formData.shift,
      medicationsInUse: formData.medications,
      allergies: formData.allergies,
      mentalHealthHistory: formData.healthHistory,
      previousDiagnoses: formData.diagnoses,
      guardianName: formData.guardianName,
      guardianPhone: formData.guardianPhone,
      guardianEmail: formData.guardianEmail,
    };

    fetch(endpoint, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Erro ao processar a solicitação.");
        }
        return response.json();
      })
      .then(() => {
        alert(
          isEditing
            ? "Aluno atualizado com sucesso!"
            : "Aluno adicionado com sucesso!"
        );
        if (!isEditing) {
          setFormData({
            firstName: "",
            lastName: "",
            birthDate: "",
            phone: "",
            gender: "",
            email: "",
            address: "",
            school: "",
            grade: "",
            classGroup: "",
            shift: "",
            medications: "",
            allergies: "",
            healthHistory: "",
            diagnoses: "",
            medicalReports: null,
            guardianName: "",
            guardianPhone: "",
            guardianEmail: "",
            observations: "",
          });
        }
      })
      .catch((error) => {
        console.error("Erro:", error);
        alert("Erro ao salvar os dados do aluno.");
      });
  };

  if (!isClient) return null;

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
            <div className="col-md-4">
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
            <div className="col-md-4">
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
            <div className="col-md-4">
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
                name="email"
                placeholder="Email"
                value={formData.email}
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
            <div className="col-md-4">
              <input
                type="text"
                name="medications"
                placeholder="Medicamentos em Uso"
                value={formData.medications}
                onChange={handleChange}
                className="form-control"
              />
            </div>
            <div className="col-md-4">
              <input
                type="text"
                name="allergies"
                placeholder="Alergias"
                value={formData.allergies}
                onChange={handleChange}
                className="form-control"
              />
            </div>
            <div className="col-md-4">
              <input
                type="text"
                name="diagnoses"
                placeholder="Diagnósticos"
                value={formData.diagnoses}
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
        {/* Outros campos do formulário */}
        <div className="text-center">
          <button type="submit" className="techwave_fn_button">
            {isEditing ? "Salvar Alterações" : "Adicionar Aluno"}
          </button>
        </div>
      </form>
    </div>
  );
}
