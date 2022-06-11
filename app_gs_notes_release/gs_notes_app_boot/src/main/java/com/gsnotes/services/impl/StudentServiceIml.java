package com.gsnotes.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IEtudiantDAO;
import com.gsnotes.dao.IInscriptionAnnuelleDAO;
import com.gsnotes.dao.IInscriptionMatiereDAO;
import com.gsnotes.dao.IInscriptionModuleDAO;
import com.gsnotes.dao.INiveauDAO;
import com.gsnotes.services.IStudentService;
import com.gsnotes.web.models.ExcelDataModel;

@Service
public class StudentServiceIml implements IStudentService {
	
	private static final String INSCRIPTION = "INSCRIPTION";
	private static final String REINSCRIPTION = "REINSCRIPTION";
	
	@Autowired
	private IEtudiantDAO etudiantDAO;
	@Autowired
	private INiveauDAO niveauDAO;
	@Autowired
	private IInscriptionAnnuelleDAO inscriptionAnnuelleDAO;
	@Autowired
	private IInscriptionModuleDAO inscriptionModuleDAO;
	@Autowired
	private IInscriptionMatiereDAO inscriptionMatiereDAO;
	
	public StudentServiceIml(IEtudiantDAO etudiantDAO, INiveauDAO niveauDAO,
			IInscriptionAnnuelleDAO inscriptionAnnuelleDAO, IInscriptionModuleDAO inscriptionModuleDAO,
			IInscriptionMatiereDAO inscriptionMatiereDAO) {
		super();
		this.etudiantDAO = etudiantDAO;
		this.niveauDAO = niveauDAO;
		this.inscriptionAnnuelleDAO = inscriptionAnnuelleDAO;
		this.inscriptionModuleDAO = inscriptionModuleDAO;
		this.inscriptionMatiereDAO = inscriptionMatiereDAO;
	}

	@Override
	public List<ExcelDataModel> importFromExcel(Workbook workbook) throws IOException {
		
		if(workbook == null) {
			throw new IOException("Excel file is null.");
		}
		
		//on crée les data à partir du fichier excel
		List<ExcelDataModel> listData = parseExcelFile(workbook);
		//on sauvegade les données en base
		List<ExcelDataModel> listToUpdate = saveExcelData(listData);
		//on retourne la liste des étudiants à updater
		return listToUpdate;
		//on 
//		int rowIndex = 0;
//		int cellIndex = 0;
//		
//		Sheet sheet = workbook.getSheetAt(0);
//		
//		Iterator<Row> rowIterator = sheet.iterator();
//		
//		while (rowIterator.hasNext()) {
//			Row row = rowIterator.next();
//			long idEtudiant = -1;
//			String cneEtudiant = null;
//			String nomEtudiant = null;
//			String prenomEtudiant = null;
//			long idNiveau = -1;
//			String typeInscription = null;
//			
//			Iterator<Cell> cellIterator = row.cellIterator();
//			
//			while (cellIterator.hasNext()) {
//				
//				Cell cell = cellIterator.next();
//				
//				if(rowIndex == 0) {
//					
//					if(cellIndex == 0) {
//						if(!"ID ETUDIANT".equals(cell.getStringCellValue())) {
//							throw new IOException("La 1ere colonne doit s'appeler ID ETUDIANT.");
//						}
//					}
//					
//					if(cellIndex == 1) {
//						if(!"CNE".equals(cell.getStringCellValue())) {
//							throw new IOException("La 2eme colonne doit s'appeler CNE.");
//						}
//					}
//					
//					if(cellIndex == 2) {
//						if(!"NOM".equals(cell.getStringCellValue())) {
//							throw new IOException("La 2eme colonne doit s'appeler NOM.");
//						}
//					}
//					
//					if(cellIndex == 3) {
//						if(!"PRENOM".equals(cell.getStringCellValue())) {
//							throw new IOException("La 3eme colonne doit s'appeler PRENOM.");
//						}
//					}
//					
//					if(cellIndex == 4) {
//						if(!"ID NIVEAU ACTUEL".equals(cell.getStringCellValue())) {
//							throw new IOException("La 4eme colonne doit s'appeler ID NIVEAU ACTUEL.");
//						}
//					}
//					
//					if(cellIndex == 5) {
//						if(!"TYPE".equals(cell.getStringCellValue())) {
//							throw new IOException("La 5eme colonne doit s'appeler TYPE.");
//						}
//					}
//				}
//				else {
//					
//					
//					if(cellIndex == 0) {
//						if(!cell.getCellType().equals(CellType.NUMERIC)) {
//							throw new IOException("La colonne ID ETUDIANT ne peut contenir que des valeurs de type numeriques.");
//						}
//						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne ID ETUDIANT ne peut pas contenir des valeurs vides.");
//						}
//						
//						idEtudiant = Double.valueOf(cell.getNumericCellValue()).longValue();
//					}
//					
//					if(cellIndex == 1) {
//						if(!cell.getCellType().equals(CellType.STRING)) {
//							throw new IOException("La colonne CNE ne peut contenir que des valeurs de type string.");
//						}
//						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne CNE ne peut pas contenir des valeurs vides.");
//						}
//						
//						cneEtudiant = cell.getStringCellValue();
//					}
//					
//					if(cellIndex == 2) {
//						if(!cell.getCellType().equals(CellType.STRING)) {
//							throw new IOException("La colonne NOM ne peut contenir que des valeurs de type string.");
//						}
//						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne NOM ne peut pas contenir des valeurs vides.");
//						}
//						
//						nomEtudiant = cell.getStringCellValue();
//					}
//					
//					if(cellIndex == 3) {
//						if(!cell.getCellType().equals(CellType.STRING)) {
//							throw new IOException("La colonne PRENOM ne peut contenir que des valeurs de type string.");
//						}
//						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne PRENOM ne peut pas contenir des valeurs vides.");
//						}
//						
//						prenomEtudiant = cell.getStringCellValue();
//					}
//					
//					if(cellIndex == 4) {
//						if(!cell.getCellType().equals(CellType.NUMERIC)) {
//							throw new IOException("La colonne ID NIVEAU ACTUEL ne peut contenir que des valeurs de type numeriques.");
//						}
//						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne ID NIVEAU ACTUEL ne peut pas contenir des valeurs vides.");
//						}
//						
//						idNiveau = Double.valueOf(cell.getNumericCellValue()).longValue();
//					}
//					
//					if(cellIndex == 5) {
//						if(!cell.getCellType().equals(CellType.STRING)) {
//							throw new IOException("La colonne TYPE ne peut contenir que des valeurs de type string.");
//						}
//						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne TYPE ne peut pas contenir des valeurs vides.");
//						}
//						
//						if(!INSCRIPTION.equalsIgnoreCase(cell.getStringCellValue()) && !REINSCRIPTION.equalsIgnoreCase(cell.getStringCellValue())) {
//							throw new IOException("La colonne TYPE ne peut pas contenir les valeurs INSCRIPTION ou REINSCRIPTION.");
//						}
//						
//						typeInscription = cell.getStringCellValue().toUpperCase();
//					}
//					
//					if(cellIndex == 5) {
//						ExcelDataModel data = new ExcelDataModel();
//						data.setIdEtudiant(idEtudiant);
//						data.setNomEtudiant(nomEtudiant);
//						data.setPrenomEtudiant(prenomEtudiant);
//						data.setCneEtudiant(cneEtudiant);
//						data.setIdNiveau(idNiveau);
//						data.setTypeInscription(typeInscription);
//						
//						listData.add(data);
//						Niveau niveau = new Niveau();
//						niveau.setIdNiveau(idNiveau);
//						
//						InscriptionAnnuelle inscriptionAnnuelle = new InscriptionAnnuelle();
//						inscriptionAnnuelle.setType(typeInscription);
//						inscriptionAnnuelle.setNiveau(niveau);
//						
//						List<InscriptionAnnuelle> listInscriptions = new ArrayList<>();
//						listInscriptions.add(inscriptionAnnuelle);
//						
//						Etudiant etudiant = new Etudiant();
//						etudiant.setIdUtilisateur(idEtudiant);
//						etudiant.setNom(nomEtudiant);
//						etudiant.setPrenom(prenomEtudiant);
//						etudiant.setCne(cneEtudiant);
//						etudiant.setInscriptions(listInscriptions);
//						
//						listEtudiants.add(etudiant);
//					}
//				}
//				
//				cellIndex++;
//			}
//			
//			
//			rowIndex++;
//			cellIndex = 0;
//		}
//		
//		importList(listData);
	}
	
	public List<ExcelDataModel> parseExcelFile(Workbook workbook) throws IOException {
		
		if(workbook == null) {
			throw new IOException("Excel file is null.");
		}
		
		List<ExcelDataModel> listData = new ArrayList<>();
		int rowIndex = 0;
		int cellIndex = 0;
		
		Sheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rowIterator = sheet.iterator();
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			long idEtudiant = -1;
			String cneEtudiant = null;
			String nomEtudiant = null;
			String prenomEtudiant = null;
			long idNiveau = -1;
			String typeInscription = null;
			
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while (cellIterator.hasNext()) {
				
				Cell cell = cellIterator.next();
				
				if(rowIndex == 0) {
					
					if(cellIndex == 0) {
						if(!"ID ETUDIANT".equals(cell.getStringCellValue())) {
							throw new IOException("La 1ere colonne doit s'appeler ID ETUDIANT.");
						}
					}
					
					if(cellIndex == 1) {
						if(!"CNE".equals(cell.getStringCellValue())) {
							throw new IOException("La 2eme colonne doit s'appeler CNE.");
						}
					}
					
					if(cellIndex == 2) {
						if(!"NOM".equals(cell.getStringCellValue())) {
							throw new IOException("La 2eme colonne doit s'appeler NOM.");
						}
					}
					
					if(cellIndex == 3) {
						if(!"PRENOM".equals(cell.getStringCellValue())) {
							throw new IOException("La 3eme colonne doit s'appeler PRENOM.");
						}
					}
					
					if(cellIndex == 4) {
						if(!"ID NIVEAU ACTUEL".equals(cell.getStringCellValue())) {
							throw new IOException("La 4eme colonne doit s'appeler ID NIVEAU ACTUEL.");
						}
					}
					
					if(cellIndex == 5) {
						if(!"TYPE".equals(cell.getStringCellValue())) {
							throw new IOException("La 5eme colonne doit s'appeler TYPE.");
						}
					}
				}
				else {
					
					
					if(cellIndex == 0) {
						if(!cell.getCellType().equals(CellType.NUMERIC)) {
							throw new IOException("La colonne ID ETUDIANT ne peut contenir que des valeurs de type numeriques.");
						}
						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne ID ETUDIANT ne peut pas contenir des valeurs vides.");
//						}
						
						idEtudiant = Double.valueOf(cell.getNumericCellValue()).longValue();
					}
					
					if(cellIndex == 1) {
						if(!cell.getCellType().equals(CellType.STRING)) {
							throw new IOException("La colonne CNE ne peut contenir que des valeurs de type string.");
						}
						
						if(cell.getStringCellValue().isEmpty()) {
							throw new IOException("La colonne CNE ne peut pas contenir des valeurs vides.");
						}
						
						cneEtudiant = cell.getStringCellValue();
					}
					
					if(cellIndex == 2) {
						if(!cell.getCellType().equals(CellType.STRING)) {
							throw new IOException("La colonne NOM ne peut contenir que des valeurs de type string.");
						}
						
						if(cell.getStringCellValue().isEmpty()) {
							throw new IOException("La colonne NOM ne peut pas contenir des valeurs vides.");
						}
						
						nomEtudiant = cell.getStringCellValue();
					}
					
					if(cellIndex == 3) {
						if(!cell.getCellType().equals(CellType.STRING)) {
							throw new IOException("La colonne PRENOM ne peut contenir que des valeurs de type string.");
						}
						
						if(cell.getStringCellValue().isEmpty()) {
							throw new IOException("La colonne PRENOM ne peut pas contenir des valeurs vides.");
						}
						
						prenomEtudiant = cell.getStringCellValue();
					}
					
					if(cellIndex == 4) {
						if(!cell.getCellType().equals(CellType.NUMERIC)) {
							throw new IOException("La colonne ID NIVEAU ACTUEL ne peut contenir que des valeurs de type numeriques.");
						}
						
//						if(cell.getStringCellValue().isEmpty()) {
//							throw new IOException("La colonne ID NIVEAU ACTUEL ne peut pas contenir des valeurs vides.");
//						}
						
						idNiveau = Double.valueOf(cell.getNumericCellValue()).longValue();
					}
					
					if(cellIndex == 5) {
						if(!cell.getCellType().equals(CellType.STRING)) {
							throw new IOException("La colonne TYPE ne peut contenir que des valeurs de type string.");
						}
						
						if(cell.getStringCellValue().isEmpty()) {
							throw new IOException("La colonne TYPE ne peut pas contenir des valeurs vides.");
						}
						
						if(!INSCRIPTION.equalsIgnoreCase(cell.getStringCellValue()) && !REINSCRIPTION.equalsIgnoreCase(cell.getStringCellValue())) {
							throw new IOException("La colonne TYPE ne peut pas contenir les valeurs INSCRIPTION ou REINSCRIPTION.");
						}
						
						typeInscription = cell.getStringCellValue().toUpperCase();
					}
					
					if(cellIndex == 5) {
						ExcelDataModel data = new ExcelDataModel();
						data.setIdEtudiant(idEtudiant);
						data.setNomEtudiant(nomEtudiant);
						data.setPrenomEtudiant(prenomEtudiant);
						data.setCneEtudiant(cneEtudiant);
						data.setIdNiveau(idNiveau);
						data.setTypeInscription(typeInscription);
						
						listData.add(data);
					}
				}
				
				cellIndex++;
			}
			
			
			rowIndex++;
			cellIndex = 0;
		}
		
		return listData;
	}
	
	@Transactional
	public List<ExcelDataModel> saveExcelData(List<ExcelDataModel> listData) {
		
		if(listData == null || listData.isEmpty()) {
			throw new IllegalArgumentException("La liste des données excel ne peut être null ou vide.");
		}
		
		//liste des étudiants existants en bd et devant être mis à jour
		//CNE, Nom et Prénom présentes dans le fichier sont différentes des données présentes en bd
		List<ExcelDataModel> listToUpdate = new ArrayList<>();
		
		for(ExcelDataModel data : listData) {
			
			//on valide que les données sont correctes
			validateExcelDataModel(data);
			
			if(INSCRIPTION.equals(data.getTypeInscription())) {
				
				//si c'est une inscription alors l'étudiant n'existe pas en bd
				//on le sauvegarde et on l'inscrit
				Niveau niveau = niveauDAO.getByIdNiveau(data.getIdNiveau());
				
				InscriptionAnnuelle inscriptionAnnuelle = new InscriptionAnnuelle();
				inscriptionAnnuelle.setNiveau(niveau);
				inscriptionAnnuelle.setType(INSCRIPTION);
				List<InscriptionAnnuelle> inscriptions = new ArrayList<>();
				inscriptions.add(inscriptionAnnuelle);
				
				Etudiant etudiant = new Etudiant();
				etudiant.setCne(data.getCneEtudiant());
				etudiant.setNom(data.getNomEtudiant());
				etudiant.setPrenom(data.getPrenomEtudiant());
				
				inscriptionAnnuelle.setEtudiant(etudiant);
				etudiant.setInscriptions(inscriptions);
				
				etudiantDAO.save(etudiant);
			}
			else {
				//sinon c'est une réinscription et l'étudiant existe déjà en bd
				//REINSCRIPTIONS
				//on récupére l'étudiant de la bd
				Etudiant etudiant = etudiantDAO.getByIdEtudiant(data.getIdEtudiant());
				
				//on vérifie si l'étudiant doit être mis à jour
				if(!data.getCneEtudiant().equals(etudiant.getCne()) 
						|| !data.getNomEtudiant().equals(etudiant.getNom())
						|| !data.getPrenomEtudiant().equals(etudiant.getPrenom())) {
					
					//Etudiant à mettre à jour on l'ajoute à la liste
					data.setAncienCneEtudiant(etudiant.getCne());
					data.setAncienNomEtudiant(etudiant.getNom());
					data.setAncienPrenomEtudiant(etudiant.getPrenom());
					data.setUpdate(true);
					listToUpdate.add(data);
				}
				else {
					//Sinon Etudiant n'a pas besoin de mise à jour on sauvegarde la réinscription
					Niveau niveau = niveauDAO.getByIdNiveau(data.getIdNiveau());
					
					InscriptionAnnuelle inscriptionAnnuelle = new InscriptionAnnuelle();
					inscriptionAnnuelle.setNiveau(niveau);
					inscriptionAnnuelle.setType(REINSCRIPTION);
					List<InscriptionAnnuelle> inscriptions = new ArrayList<>();
					inscriptions.add(inscriptionAnnuelle);
					
					inscriptionAnnuelle.setEtudiant(etudiant);
					etudiant.setInscriptions(inscriptions);
					
					etudiantDAO.save(etudiant);
				}
			}
		}
		
		for(ExcelDataModel data : listToUpdate) {
			System.out.println(data.getIdEtudiant()
					+ " "
					+ data.getCneEtudiant()
					+ " "
					+ data.getNomEtudiant()
					+ " "
					+data.getPrenomEtudiant()
					+ " "
					+ data.getIdNiveau()
					+ " "
					+ data.getTypeInscription());
		}
		
		return listToUpdate;
	}
	
	//validation des régles de gestion
	public void validateExcelDataModel(ExcelDataModel data) {
		
		if(data != null) {
			
			String cneEtudiant = data.getCneEtudiant();
			long idEtudiant = data.getIdEtudiant();
			long idNiveau = data.getIdNiveau();
			String nomEtudiant = data.getNomEtudiant();
			String prenomEtudiant = data.getPrenomEtudiant();
			String typeInscription = data.getTypeInscription();
			
			//tous les champs de data sont obligatoires
			if(cneEtudiant == null || cneEtudiant.isBlank() 
					|| idEtudiant < 0 
					|| idNiveau < 0 
					|| nomEtudiant == null || nomEtudiant.isBlank()
					|| prenomEtudiant == null || prenomEtudiant.isBlank()
					|| typeInscription == null || typeInscription.isBlank()) {
				
				throw new IllegalArgumentException("Les champs cneEtudiant, idEtudiant, idNiveau, nomEtudiant, prenomEtudiant et typeInscription sont obligatoires.");
			}
			
			//l'identifiant du niveau doit exister en bd
			if(!niveauDAO.existsById(idNiveau)) {
				throw new IllegalArgumentException("INSCRIPTION: Le niveau ID NIVEAU = " + idNiveau + "n'existe pas en bd.");
			}
			
			if(INSCRIPTION.equals(typeInscription)) {
				//si c'est une inscription l'étudiant ne doit pas exister en bd
				if(etudiantDAO.existsById(data.getIdEtudiant())) {
					throw new IllegalArgumentException("INSCRIPTION: L'étudiant ID ETUDIANT = " + data.getIdEtudiant() + " existe déjà en bd.");
				}
			}
			else
				if(REINSCRIPTION.equals(typeInscription)) {
					//si c'est une reinscription l'étudiant doit exister en bd
					if(!etudiantDAO.existsById(data.getIdEtudiant())) {
						throw new IllegalArgumentException("REINSCRIPTION: L'étudiant ID ETUDIANT = " + data.getIdEtudiant() + " n'existe pas en bd.");
					}
				}
				else
					throw new IllegalArgumentException("TYPE: valeure invalide: " + typeInscription);
		}
	}
	
	@Override
	public void updateStudentsExcel(List<ExcelDataModel> listModel) throws IOException {
		
		if(listModel == null || listModel.isEmpty()) {
			throw new IOException("La liste à mettre à jour est vide.");
		}
		
		for(ExcelDataModel model : listModel) {
			
			if(model.isUpdate() && etudiantDAO.existsById(model.getIdEtudiant())) {
				Etudiant etudiant = etudiantDAO.getByIdEtudiant(model.getIdEtudiant());
				etudiant.setCne(model.getCneEtudiant());
				etudiant.setNom(model.getNomEtudiant());
				etudiant.setPrenom(model.getPrenomEtudiant());
				
				etudiantDAO.save(etudiant);
			}
		}
	}
	
	public IEtudiantDAO getEtudiantDAO() {
		return etudiantDAO;
	}

	public void setEtudiantDAO(IEtudiantDAO etudiantDAO) {
		this.etudiantDAO = etudiantDAO;
	}

	public INiveauDAO getNiveauDAO() {
		return niveauDAO;
	}

	public void setNiveauDAO(INiveauDAO niveauDAO) {
		this.niveauDAO = niveauDAO;
	}

	public IInscriptionAnnuelleDAO getInscriptionAnnuelleDAO() {
		return inscriptionAnnuelleDAO;
	}

	public void setInscriptionAnnuelleDAO(IInscriptionAnnuelleDAO inscriptionAnnuelleDAO) {
		this.inscriptionAnnuelleDAO = inscriptionAnnuelleDAO;
	}

	public IInscriptionModuleDAO getInscriptionModuleDAO() {
		return inscriptionModuleDAO;
	}

	public void setInscriptionModuleDAO(IInscriptionModuleDAO inscriptionModuleDAO) {
		this.inscriptionModuleDAO = inscriptionModuleDAO;
	}

	public IInscriptionMatiereDAO getInscriptionMatiereDAO() {
		return inscriptionMatiereDAO;
	}

	public void setInscriptionMatiereDAO(IInscriptionMatiereDAO inscriptionMatiereDAO) {
		this.inscriptionMatiereDAO = inscriptionMatiereDAO;
	}
}
