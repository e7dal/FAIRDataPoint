/**
 * The MIT License
 * Copyright © 2017 DTL
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package nl.dtls.fairdatapoint.api.controller.dashboard;

import nl.dtls.fairmetadata4j.model.FDPMetadata;
import nl.dtls.fairdatapoint.api.controller.metadata.MetadataController;
import nl.dtls.fairdatapoint.api.dto.dashboard.DashboardCatalogDTO;
import nl.dtls.fairdatapoint.service.dashboard.DashboardService;
import nl.dtls.fairdatapoint.service.metadata.common.MetadataServiceException;
import org.eclipse.rdf4j.model.IRI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static nl.dtls.fairdatapoint.util.IRIUtils.removeLastPartOfIRI;

@RestController
@RequestMapping("/dashboard")
public class DashboardController extends MetadataController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DashboardCatalogDTO>> getDashboard(HttpServletRequest request) throws MetadataServiceException {
        IRI uri = getRequestURLasIRI(request);
        IRI repositoryUri = removeLastPartOfIRI(uri);
        FDPMetadata metadata = repositoryMetadataService.retrieve(repositoryUri);
        List<DashboardCatalogDTO> dto = dashboardService.getDashboard(metadata);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}